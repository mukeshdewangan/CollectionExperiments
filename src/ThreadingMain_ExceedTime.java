import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ThreadingMain_ExceedTime {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("first",1);
        map.put("second",2);
        map.put("third", 3);
        map.put("fourth", 4);

        System.out.println(map.keySet());

        System.out.println(Thread.currentThread().getName() + " is executing " + Instant.now());
        //RunThreads();
        System.out.println(Thread.currentThread().getName() + " is ending " + Instant.now());
    }

    public static void RunThreads() {

        Thread oldWay = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is executing " + Instant.now());
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("RANDOM " + Math.random());
                }
                System.out.println(Thread.currentThread().getName() + " is executing " + Instant.now());
            }
        });
        oldWay.start();
    }
}
