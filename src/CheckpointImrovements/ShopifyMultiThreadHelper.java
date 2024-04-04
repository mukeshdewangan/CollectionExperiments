package CheckpointImrovements;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/** This is a helper class having methods to perform the shopify API calling using a thread pool. */
public class ShopifyMultiThreadHelper {
    private static final int PARALLELISM = 8;
    static int jobsPerBatch = 24;
    private final Duration jobDuration = Duration.ofHours(1);

    /**
     * Using a thread pool, make Shopify api calls in parallel.
     *
     * <p>In order to fetch Orders in parallel, it is required to fetch Orders based on Duration opposite to sequential
     * approach which performed via ID based fetching. A fixed number of threads fetch records in parallel. Each thread
     * fetch records for a fixed duration like 5 days e.g. thread-1 will fetch from 1 Jan 20XX to 5 Jan 20XX while
     * thread-2 will fetch from 6 Jan 20XX to 10 Jan 20XX and so on. Once records are fetched, these are processed
     * sequential by core. Note: Inner API calls are also sequential at this point of time which can be turn into
     * parallel calls if we get higher number of API limit in the future.
     */

    void doParallelFetch(Instant periodStartTime, Instant periodEndTime, BiConsumer<Instant, Instant> updateMethod) {
        System.out.println("doParallelFetch called for " + periodEndTime + " till "+ periodEndTime);
        Instant previousBatchEndTime = periodStartTime;

        do {
            final ExecutorService threadPool = Executors.newFixedThreadPool(PARALLELISM);
            final Scheduler scheduler = Schedulers.fromExecutor(threadPool);
            List<ShopifyFetchJob> fetchJobList =
                    getFetchJobsForPeriod(previousBatchEndTime, periodEndTime, jobDuration);

            Flux.fromIterable(fetchJobList)
                    .parallel(PARALLELISM)
                    .runOn(scheduler)
                    .doOnNext(job -> updateMethod.accept(job.startTime, job.endTime))
                    .sequential()
                    .blockLast();
            threadPool.shutdown();
            System.out.println("checkpoint");
            previousBatchEndTime = fetchJobList.get(fetchJobList.size() - 1).endTime;
        } while (previousBatchEndTime.isBefore(periodEndTime));
    }

    /**
     * Create record fetching jobs from Shopify server. Each job is responsible for fetching records of the specified
     * duration
     *
     * @param batchStartTime batch start time
     * @param syncEndTime batch end time
     * @param batchJobDuration period of a job
     * @return List of ShopifyFetchJob
     */
    List<ShopifyFetchJob> getFetchJobsForPeriod(
            Instant batchStartTime, Instant syncEndTime, Duration batchJobDuration) {
        List<ShopifyFetchJob> fetchJobList = new ArrayList<>();
        System.out.println("getFetchJobsForPeriod is called for start "+ batchStartTime + " syncEndTime "+syncEndTime );
        Instant previousJobEndTime = batchStartTime;

        for (int batchIndex = 0; batchIndex < jobsPerBatch && previousJobEndTime.isBefore(syncEndTime); batchIndex++) {
            Instant jobStartTime = previousJobEndTime;
            Instant jobEndTime = jobStartTime.plus(batchJobDuration);
            jobEndTime = jobEndTime.isAfter(syncEndTime) ? syncEndTime : jobEndTime;
            ShopifyFetchJob fetchJob = new ShopifyFetchJob(jobStartTime, jobEndTime);
            fetchJobList.add(fetchJob);
            previousJobEndTime = jobEndTime;
        }

        System.out.println("Returning job list of size "+ fetchJobList.size());
        return fetchJobList;
    }

    /** Local class for storing the Fetch Job */
    static class ShopifyFetchJob {
        public final Instant startTime;
        public final Instant endTime;

        ShopifyFetchJob(Instant start, Instant end) {
            startTime = start;
            endTime = end;
        }

        @Override
        public String toString() {
            return "ShopifyFetchJob{" + "startTime=" + startTime + ", endTime=" + endTime + '}';
        }
    }
}

