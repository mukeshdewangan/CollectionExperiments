package ThreadInterruption;

import java.lang.*;
import java.util.*;
public class getThreadStack  implements Runnable {
    public void run() {
        System.out.println("This is run() method");
    }

    public static void main(String args[]) {
        getThreadStack trace = new getThreadStack();
        Thread t = new Thread(trace);

        // this will call run() method
        t.start();

        // returns a map of stack traces
        Map m = Thread.getAllStackTraces();


    }
}
