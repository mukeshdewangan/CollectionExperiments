package TryCatchFinally;

import java.security.KeyException;
import java.util.MissingResourceException;
import java.util.Optional;


public class TryCatchFinallyExperiment {
    public static void main(String[] args) {

        /*
        Exception exception = new RuntimeException("Having trouble");
        exception.addSuppressed(new RuntimeException("INNER Exception"));
        //Throwable th = new RuntimeException( exception);// exception;
        Throwable th = new Exception( exception);

        th.getCause();
        Throwable[] arr = th.getSuppressed();
        System.out.println(arr.length);

        RuntimeException[] exceptionArr = new RuntimeException[3];
        exceptionArr[0] = new RuntimeException(new NullPointerException());
        exceptionArr[1] = new RuntimeException(new ArithmeticException());
        exceptionArr[2] = new RuntimeException(new KeyException());

        //Optional<NullPointerException> npe = Exceptions.findCause( exceptionArr[0], NullPointerException.class);
        System.out.println("");

        for (int i = 0; i < exceptionArr.length; i++) {
            System.out.println(exceptionArr[i]);
        }

         */
        try {
            temp();
        }catch (Throwable e){
            System.out.println( "suppressed length " + e.getSuppressed().length);
            Throwable cause = e.getCause();
            System.out.println( "cause's suppressed length " + cause.getSuppressed().length);
            e.printStackTrace();
            System.out.println(" suppressed exception - " + e.getSuppressed().length);
        }

    }

    public static void temp() throws Throwable {
        Throwable updateException = null;
        try{
            update();
        } catch (Throwable e){
            Log(e);
            updateException = e;
        } finally {
            try {
                output_close();
            } catch (IllegalArgumentException ex) {
                Log(ex);
            } catch (Exception err){
                Log(err);
                Throwable tempException = updateException;
                updateException = err;
                if (tempException != null) {
                    updateException.addSuppressed(tempException);
                }
            }finally {
                System.out.println("Update dataExtractionFinishedAt");
            }
        }

        if(updateException!=null){
            throw new RuntimeException(updateException);
            //throw updateException;
        }
    }

    public static void update(){
        System.out.println("service.update()");
        //System.out.println("Connector throwing exception");
        throw new RuntimeException("CONNECTOR ERROR : API PERMISSION MISSING");
        //throw new RuntimeException("CONNECTOR ERROR : API PERMISSION MISSING");
    }

    public static void output_close(){
        System.out.println("Output.close()");
        System.out.println("Writer throwing exception");
        //throw new RuntimeException("WRITE TO WAREHOUSE FAILED");
        throw new IllegalArgumentException("having illegal args");
        //System.out.println("Writer successfully flushed data");
    }

    public static void Log(Throwable e){
        System.out.println("Logging Throwable " + e.getMessage());
    }
}
