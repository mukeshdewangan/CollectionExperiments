package CheckpointImrovements;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MultithreadHelperTests {
    public static void main(String[] args) {
        MultithreadHelperTests m = new MultithreadHelperTests();
        m.driverMethod();
    }
    void updateOrdersInSequential(Instant since, Instant until)  {
        System.out.println("processing for start "+ since + " until " + until);
        try {
            Instant tempStart = since;
            while (until.isAfter(tempStart)) {
                tempStart = tempStart.plus(10, ChronoUnit.MINUTES);
            }
        }catch (Exception ex){
            System.out.println("Interrupted exception ex");
        }
    }

    private void driverMethod(){
        ShopifyMultiThreadHelper shopifyMultiThreadHelper = new ShopifyMultiThreadHelper();
        Instant start = Instant.parse("2022-06-15T18:35:24.00Z");
        Instant end = Instant.parse("2022-06-18T12:35:24.00Z");
        shopifyMultiThreadHelper.doParallelFetch(start, end,  this::updateOrdersInSequential);
    }

}
