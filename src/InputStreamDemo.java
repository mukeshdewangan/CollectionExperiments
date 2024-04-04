import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class InputStreamDemo {
    public static void main(String[] args) throws Exception {
        InputStream is = null;
        int j;
        char c;

        try {
            // new input stream created
            is = new FileInputStream("/Users/mukeshdewangan/Downloads/input.txt");

            System.out.println("Characters printed:");
            Instant heartbeat = Instant.now().minus(Duration.ofMinutes(5));
            System.out.println( Timestamp.from(heartbeat));
            // reads till the end of the stream
            while((j = is.read())!=-1) {

                // converts integer to character
                //c = (char)i;
                byte[] buffer = new byte[3];
                is.read(buffer);

                int i = 0;

                for (byte b: buffer) {
                    //System.out.printf("%02x ", b);
                    System.out.print((char) b);
                    i++;
                }
                System.out.println();
                // prints character
                //System.out.print(c);
            }

        } catch(Exception e) {
            // if any I/O error occurs
            e.printStackTrace();
        } finally {
            // releases system resources associated with this stream
            if(is!=null)
                is.close();
        }
    }
}