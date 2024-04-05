package VaultHacking;

public class SuppressException {
    public static void main(String[] args) throws Exception
    {
        try {
            testException();
        }
        catch (Throwable e) {
            // get StackTraceElements
            System.out.println(e.getMessage());
            Throwable[] suppExe = e.getSuppressed();
            // print element of suppExe
            for (int i = 0; i < suppExe.length; i++) {
                System.out.println("Suppressed Exceptions:");
                System.out.println(suppExe[i]);
            }
        }
    }

    // method which throws Exception
    public static void testException() throws Exception
    {
        // creating a suppressed Exception
        Exception suppressed = null;//new ArithmeticException();
        // creating a IOException object
        final Exception exe = new Exception("The main excpetion to surface");

        // adding suppressed Exception
        // using addSuppressed method
        exe.addSuppressed(suppressed);
        // throwing IOException
        throw exe;
    }
}
