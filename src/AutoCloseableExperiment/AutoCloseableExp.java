package AutoCloseableExperiment;

public class AutoCloseableExp {
    public void Try(){
        System.out.println("First is called");
        throw new RuntimeException("exception from try");
    }

    public void Catch(){
        System.out.println("Catch is called");
    }
    public void Final(){
        System.out.println("Final is called");
    }

    public int getInt(){
        System.out.println("Returning from getInt");
        return 100;
    }

    public static int dummyMethod(){
        AutoCloseableExp at = new AutoCloseableExp();
        try{
            at.Try();
        }catch (Exception e){
            at.Catch();
            return at.getInt();
        }finally {
            at.Final();
        }
        return 0;
    }
    public static void main(String[] args) {
        int a = dummyMethod();
        System.out.println("Returned from dummyMethod " + a);
    }
}
