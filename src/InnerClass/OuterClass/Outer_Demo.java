package InnerClass.OuterClass;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Outer_Demo {
    int num;

    public static void main(String[] args) {
        Outer_Demo demo = new Outer_Demo();
        System.out.println(demo.getConnection());
    }
    public int getConnection(){
        try{
            System.out.println("preparation");
            String repeatedString = Stream.generate(() -> "?")
                    .limit(5)
                    .collect(Collectors.joining(", "));
            System.out.println(repeatedString);
            return 3;
        }
        catch (Exception ex){
            System.out.println("exception " + ex.getMessage());
            throw ex;
        }
        finally {
            System.out.println("clean-up");
        }
    }
    // inner class
    private class Inner_Demo {
        public void print() {
            System.out.println("This is an inner class");
        }
    }

    // Accessing he inner class from the method within
    void display_Inner() {
        Inner_Demo inner = new Inner_Demo();
        inner.print();
    }
}