package MultithreadingStateMutation.AtomicExperiment;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Driver {
    public static void main(String[] args) {
        int consumersNumber = 8;
        DonutStorage donutStorage = new DonutStorage(20);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

       // List<Future<?>> futures = new ArrayList<>(consumersNumber);

        for (int i = 0; i < consumersNumber; i++) {
            //futures.add(executor.submit(() ->
                    new Consumer(donutStorage).consume(1);
            //));
        }
        executor.shutdown();

//        for (Future<?> future: futures) {
//            try{
//                future.get();
//            }
//            catch (InterruptedException| ExecutionException e){
//                System.out.println("Exception while getting from future" + e.getMessage());
//                e.printStackTrace();
//            }
//        }
        while(!executor.isTerminated()){
            System.out.println("not terminated, still waiting");
        }
        System.out.println("Number of remaining donuts: " + donutStorage.getDonutsNumber());
    }
}