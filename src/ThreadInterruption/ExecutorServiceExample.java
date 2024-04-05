package ThreadInterruption;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {



    private static void executorRunningExample(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long startTime = System.currentTimeMillis();

        // Submit the even number thread
        executorService.submit(() -> {
            for (int i = 2; i <= 10; i += 2) {
                performComputation(i);
            }
            System.out.println("Even Thread ends");
        });

        // Submit the odd number thread
        executorService.submit(() -> {
            for (int i = 1; i <= 10; i += 2) {
                performComputation(i);
            }
            System.out.println("Odd Thread ends");
        });

        // Shutdown the executor service
        executorService.shutdown();

        // Wait for both tasks to complete
        while (!executorService.isTerminated()) {
            // Wait until all tasks are finished
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;



        System.out.println("Total execution time: " + totalTime + " milliseconds");
    }

    public static void sequentialExample() {
        long startTime = System.currentTimeMillis();

        // Perform computationally heavy operation on even numbers till 1000
        for (int i = 2; i <= 1000; i += 2) {
            performComputation(i);
        }
        System.out.println("Even Thread ends");

        // Perform computationally heavy operation on odd numbers till 10000
        for (int i = 1; i <= 10000; i += 2) {
            performComputation(i);
        }
        System.out.println("Odd Thread ends");

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Total execution time: " + totalTime + " milliseconds");
    }

    private static double performComputation(int number) {
        // Simulate a computationally heavy operation (Calculating square root without using library method)
        double result = 0;
        for (int i = 0; i < 100000; i++) {
            result += squareRoot(number);
        }
        return result;
    }

    private static double squareRoot(int number) {
        // Custom implementation of square root calculation
        double approx = number / 2.0;
        double better;
        do {
            better = approx;
            approx = (better + (number / better)) / 2.0;
        } while ((better - approx) != 0);
        return approx;
    }



    public static void main2(String[] args) throws InterruptedException {
        //printNumbers2();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<?> future = executor.submit(() -> {
            printNumbers(); // first call
            printNumbers(); // second call
        });
        Thread.sleep(3_000);
        executor.shutdownNow();  // will interrupt the task
        executor.awaitTermination(3, TimeUnit.SECONDS);
    }

    private static Runnable taskThatFinishesEarlyOnInterruption() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i);      // requirement 1
                try {
                    Thread.sleep(1_000);  // requirement 2
                } catch (InterruptedException e) {
                    break;                // requirement 7
                }
            }
        };
    }

    private static class BlockingTaskThread2 implements Runnable{
        @Override
        public void run() {
            //System.out.println(Instant.now().getEpochSecond());
        }
    }
    private static void runOnce(Thread t){
        System.out.println("Thread is " + t.getState());
        for (int i = 0; i < 2; i++) {
            try {
                t.sleep(1_000);
                System.out.println(Instant.now().getEpochSecond());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred for " + t.getName());
                //t.interrupt(); // preserve interruption status
                //throw  new RuntimeException(e.getMessage());
                //break;
            }
        }

    }

    private static void printNumbers2() throws InterruptedException {
        Thread t = new Thread(new BlockingTaskThread2());
        t.start();
        for (int i = 0; i < 2; i++) {
            runOnce(t);
        }
        Thread.sleep(1000);
        t.interrupt();
    }

    private static void printNumbers() {
        for (int i = 0; i < 10; i++) {
            System.out.println( Thread.currentThread().getName() +":" +  i);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve interruption status
                //throw  new RuntimeException(e.getMessage());
                break;
            }
        }
    }
}
