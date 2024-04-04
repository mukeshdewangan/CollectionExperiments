package MultithreadingStateMutation;

import java.util.concurrent.atomic.AtomicInteger;

public class State {
    //Integer cursor = 0;
    AtomicInteger cursor1 = new AtomicInteger(0);
    public void increment(){
        //cursor++;
        cursor1.incrementAndGet();
    }

    public Integer getCursor(){
        //return cursor;
        return cursor1.get();
    }
}