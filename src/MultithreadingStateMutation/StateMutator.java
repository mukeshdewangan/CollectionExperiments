package MultithreadingStateMutation;

public class StateMutator  extends java.lang.Thread{
    MultithreadingStateMutation.State state ;
    public void run()
    {
        System.out.println("Thread started");
        for(int i =0; i<1000; i++){
            state.increment();
        }
        System.out.println(state.getCursor());
    }
    StateMutator(MultithreadingStateMutation.State passedState){
        this.state = passedState;
    }
}
