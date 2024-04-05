import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String... args) throws InterruptedException {
            ImplementRunMethod.RunThreads();
            NewThread nt = new NewThread();
            nt.start();
    }
}
