package ThreadInterruption;


import java.time.Instant;

public class BlockingTask {

    public static void main2(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(taskThatFinishesEarlyOnInterruption());
        taskThread.start();      // requirement 3
        Thread.sleep(3_000);     // requirement 4
        taskThread.interrupt();  // requirement 5
        taskThread.join(20_000);  // requirement 6
    }

    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(sampleInterruption());
        taskThread.start();

        interruptThread(taskThread);
        Thread.sleep(5000);
        System.out.println("taskThread.isInterrupted -" + taskThread.isInterrupted());
    }

    private static void interruptThread(Thread taskThread) throws InterruptedException{
        Thread.sleep(2000);
        taskThread.interrupt();
    }

    private static Runnable sampleInterruption() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("IN thread " + Thread.currentThread().getName() + " "+ i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Having issue with this thread.sleep() - Error Message:" + e.getMessage());
                }
        }};
    }

    private static Runnable taskThatFinishesEarlyOnInterruption() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i);      // requirement 1
                try {
                    Thread.sleep(1_000);  // requirement 2
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    throw new RuntimeException("Having issue with this thread.sleep() - Error Message:" + e.getMessage());
                    //break;                // requirement 7
                }
            }
        };
    }

    public static void main1(String[] args) throws InterruptedException {


        /*Thread blockingThread = new Thread(new BlockingTaskThread());
        //blockingThread.run();
        System.out.println(Instant.now());
        blockingThread.start();
        blockingThread.setName("SLEEP");

        Thread.sleep(3000L);

        //blockingThread.interrupt();
        System.out.println(Instant.now());

         */
        Thread series = new Thread(new PrintSeries());
        System.out.println("Before starting series " + Instant.now());
        System.out.println("Main thread "+ Thread.currentThread().getName());
        series.start();           // 3
        Thread.sleep(3_000);// 4
        //series.interrupt();       // 5
        series.join(1_000); // 6
        System.out.println("After Join " + Instant.now());
    }

    private static class PrintSeries implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10 ; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);// 1
                try{
                    Thread.sleep(1_000);    // 2
                } catch (InterruptedException e) {
                    break;                        // 7
                }
            }
        }
    }
    private static class BlockingTaskThread implements Runnable{
        @Override
        public void run() {

            try {
                System.out.println("Starting the Blocking thread "+  Thread.currentThread().getName() + " at " + Instant.now());
                Thread.sleep(5000);

            }catch (InterruptedException e) {
                System.out.println("Caught the Interruption " + Thread.currentThread().getName() + " thread " + " at " + Instant.now() );
                System.out.println(Thread.currentThread().isInterrupted() + " ");
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted() + " ");

                //e.printStackTrace();
            }
            System.out.println("Completed the thread "+  Thread.currentThread().getName());
        }
    }
}
