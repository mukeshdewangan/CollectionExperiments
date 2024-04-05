package MultithreadingStateMutation;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class StateModifier {
    static State state = new State();
//    public static void main(String[] args) {
//        System.out.println("Starting main");
//        for (int n = 1; n <= 100; n++) {
//            StateMutator t = new StateMutator(state);
//            t.start();
//        }
//        System.out.println("ending main" + state.getCursor());
//    }

    enum Endpoint {
        FIRST,
        SECOND,
        THIRD
    }
    public static void main(String[] args) {

        Map<String, Integer> eventCounter = new HashMap<>();
        eventCounter.put("invoice", 90);
        eventCounter.put("customer", 230);
        eventCounter.put("payment", 210);

        System.out.println(eventCounter);


        String str = "third";
        String third1 = "THIRD";
        Endpoint endpoint =  Endpoint.valueOf(third1);

        System.out.println(endpoint);

        if(Endpoint.THIRD.equals(endpoint)){
            System.out.println("GOOD");
        }
        else {
            System.out.println("BAD");
        }

        if(!Endpoint.FIRST.equals(null))
        {
            System.out.println("FIRST compared to NULL");
        }

        if(Endpoint.THIRD.equals(null)){
            System.out.println("THIRD compared to NULL");
        }

        Endpoint endpoint1 = Endpoint.valueOf(str);
        System.out.println(endpoint1);



    }
}



