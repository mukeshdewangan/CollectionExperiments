import java.time.Instant;

public class ImplementRunMethod {
    public static void RunThreads() throws InterruptedException{

        Thread oldWay = new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i =0 ;i < 89; i++) {
                    System.out.println("RANDOM "+ Math.random());
                }
            }
        });


        System.out.println("Current Time " + Instant.now());
        Thread th = new Thread(() -> {
            //System.out.println("Before executing 100 loops  Current Time " + Instant.now());
            System.out.println("Thread Name " + Thread.currentThread().getName());
            for (int i = 0; i < 200; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("value " + i);
                System.out.println("EVEN");
            }
            //System.out.println("function name Thread1" + Thread.currentThread().getClass().getEnclosingMethod().getName());
            //System.out.println("After executing 100 loops  Current Time " + Instant.now());
        }
        );


        Thread th2 = new Thread(() -> {
            //System.out.println("Before executing 100 loops  Current Time " + Instant.now());
            System.out.println("Thread Name " + Thread.currentThread().getName());
            for (int i = 0; i < 200; i++){
                //Thread.sleep(100);
                //System.out.println("value " + i);
                System.out.println("ODD");
            }
            //System.out.println("function name Thread2 " + Thread.currentThread().getClass().getEnclosingMethod().getName());
            //System.out.println("After executing 100 loops  Current Time " + Instant.now());
        }
        );
        th.start();
        th2.start();
        oldWay.start();


    }
}
