package ThreadInterruption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TryCatchFinally {
    public static void main(String[] args) {

        int a = 7;
        int b = 7;
        int c = 7;
        //System.out.println(" done " + (c == null));
        long result = multiplyThreeIntegers(a,b,c);
        //System.out.println(result);
        try {
            doSomethingWhichThrowsException();
            System.out.println("OK");
        } catch (RuntimeException e) {
            System.out.println("ERROR "  + e.getMessage());
        }catch ( Throwable e){
            System.out.println("Throwable "  + e.getMessage());
        }

        finally {
            System.out.println("IN Finally, Should be executed every time");
        }
    }

    public static long multiplyThreeIntegers(int a, int b, int c){

        List<Float> weight = new ArrayList<>();
        weight.add( 1.01f);
        weight.add( 2.01f);
        weight.add( 1.4f);weight.add( 1.61f);weight.add(1.31f);

        List<Float> result = new ArrayList<>();
        Collections.sort(weight);
        int start = 0, end = weight.size()-1;

        while( start < end){
            // get a[start] + a[end] if greater than 3.00 then increment end and put in the result;
            // else if sum is smaller than 3.00 , put in result -> then decrement end and increment start ;
            Float sum = weight.get(start) + weight.get(end);
            System.out.println("sum "+ sum);
            if(sum >= 3.00f){
                System.out.println("If");
                result.add(weight.get(end));end--;
            }
            else{
                System.out.println("Else");
                result.add(sum); end--; start++;
            }
        }
        if(start == end){
            result.add(weight.get(start));
        }
        //return result.size();
       long res = (long)a * b * c;
        return res;
    }

    public static void doSomethingWhichThrowsException() throws Throwable {
        try {
            throw new RuntimeException("from try - doSomething");
        } catch(Exception e){
            System.out.println("Caught exception "+ e.getMessage());
            throw new RuntimeException("from catch - doSomething");
        }
        finally {
            //throw new RuntimeException("from finally - doSomething");
            throw new Throwable("Something bad has happened");
        }
    }
}
