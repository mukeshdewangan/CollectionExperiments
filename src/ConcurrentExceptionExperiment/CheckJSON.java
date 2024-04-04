package ConcurrentExceptionExperiment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

class State {
    public Integer member1;
    public String member2;
    public JsonNode jsonNode;
}

public class CheckJSON {
    static ObjectMapper objectMapper = new ObjectMapper();

    private static int ElementCount = 1000;

    private static MandrillState randomMandrillState(){
        Integer RandomNumber[] = new Integer[ElementCount];
        for (int i = 0; i < ElementCount ; i++) {
            RandomNumber[i] = (int) (Math.random() * ElementCount);
        }
        MandrillState state = null;// = new MandrillState(RandomNumber, Math.random(), new HashMap<String,String>());
        return state;
    }

    private static JsonNode getStateString(MandrillState state) throws IOException {
        String returnStr = objectMapper.writeValueAsString(state);
        //objectMapper.writeValue(new File("mandrill.json"), state);
        //JsonNode node = objectMapper.valueToTree(state);

        JsonNode node = objectMapper.convertValue(state, JsonNode.class);

        //System.out.println(node.get("arr"));
        return node;
    }



    private static MandrillState getMandrill(JsonNode node) {
        //String input = "{\"arr\":[1,4,2,7,2],\"aDouble\":0.35694664759565464}";
        MandrillState state = objectMapper.convertValue(node, MandrillState.class);
        return state;
    }

    public static void main_2(String[] args) throws IOException {
        /*
        MandrillState state1 = randomMandrillState();
        JsonNode createdFromGetState =  getStateString(state1);
        System.out.println(createdFromGetState);

        //System.out.println("Created from the Random Mandrill state");

        MandrillState state = getMandrill(createdFromGetState);
        //System.out.println( "Double " + state.aDouble);
        /*for (int i = 0; i < state.arr.length ; i++) {
            System.out.print(state.arr[i] + ",");
        }

        List<Integer> first = new ArrayList<>(); first.add(23); first.add(25); first.add(5);

        List<Integer> second  = new ArrayList<>(); second.add(23); second.add(55); second.add(5);

        //assert first.equals(second);
        if( first.equals(second)) {
            System.out.println("equals");
        }
        else
            System.out.println("not equals");


        if(Arrays.equals(state1.arr, state.arr)) System.out.println("arr are equals");
        if(state1.random.equals(state.random)) System.out.println("random list are equals");

    */
        //String nodeStr1 = "{\"arr\":[110,240,376,185],\"aDouble\":0.12443,\"random\":{\"list\":[23,13,28]}}";
        String nodeStr1 = "{\"strName\":\"Mukesh\",\"double\":0.12443}";
        System.out.println(nodeStr1);
        String nodeStr2 = "{\"arr\":[110,240,376,185],\"random\":{\"list\":[23,13,28], \"aDouble\":0.12443}}";
        System.out.println(nodeStr2);

        List<Integer> randomList1 = new ArrayList<>(); randomList1.add(23); randomList1.add(13); randomList1.add(28);

        List<Integer> randomList2 = new ArrayList<>(); randomList2.add(23); randomList2.add(13); randomList2.add(28);


        Map<String, String> map = new HashMap<>();
        map.put("3pgfps", "first");
        map.put("random", "kochki");
        map.put("secondRandom", "billi");
        map.put("3pgfr5", "second");
        MandrillState state1 = new MandrillState(new Integer[]{110,240,376,185}, 0.12443, map, PiiType1.PASSPORT);
        MandrillState state2 = new MandrillState(new Integer[]{110,240,376,185}, 0.12443, map, PiiType1.EMAIL_ADDRESS);

        JsonNode node1 = objectMapper.convertValue(state1, JsonNode.class);
        JsonNode node2 = objectMapper.convertValue(state2, JsonNode.class);

        node1 = objectMapper.convertValue(state1, JsonNode.class);
        node2 = objectMapper.convertValue(state2, JsonNode.class);

        //String str1 = "{\"arr\":[110,240,376,185],\"aDouble\":0.12443,\"random\":{\"list\":[23,13,28]}}";
        //String str2 = "{\"random\":{\"list\":[23,13,28]},\"arr\":[110,240,376,185],\"aDouble\":0.12443}";
        node1 = objectMapper.convertValue(state1, JsonNode.class);
        node2 = objectMapper.convertValue(state2, JsonNode.class);


        MandrillState state11 = objectMapper.convertValue(node1, MandrillState.class);
        MandrillState state21 = objectMapper.convertValue(node2, MandrillState.class);

        node1 = objectMapper.convertValue(state11, JsonNode.class);
        node2 = objectMapper.convertValue(state21, JsonNode.class);

        if(node1.equals(node2)){
            System.out.println("EQUAL");
        }else{
            System.out.println("NOT EQUAL");
        }
    }

    public static void main(String[] args) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        //List<Integer> randomList1 = new ArrayList<>(); randomList1.add(11); randomList1.add(21); randomList1.add(31);
        //List<Integer> randomList2 = new ArrayList<>(); randomList2.add(11); randomList2.add(21); randomList2.add(31);

        Map<String,String> map = new HashMap<>();
        map.put("First","1");
        map.put("Second", "3");

        MandrillState state1 = new MandrillState(new Integer[]{110,210,310}, 0.12443, map , PiiType1.ADDRESS);
        //MandrillState state2 = new MandrillState(new Integer[]{110,210,310}, 0.12423 , PiiType1.PASSPORT);
        objectMapper.writeValue(new File("state1.json"), state1);
        //objectMapper.writeValue(new File("state2.json"), state2);

        MandrillState2 state12 = objectMapper.readValue(new File("state1.json"), MandrillState2.class);
        //MandrillState2 state22 = objectMapper.readValue(new File("state2.json"), MandrillState2.class);
        objectMapper.writeValue(new File("state2.json"), state12);
        //JsonNode node1 = objectMapper.convertValue(state12, JsonNode.class);
        //JsonNode node2 = objectMapper.convertValue(state22, JsonNode.class);

        //if(node1.equals(node2)) System.out.println("nodes are equal");
        //else System.out.println("nodes are NOT equal");
        System.out.println(state12.type1);
    }

    public static void main_3(String[] args) throws IOException {
        //testCompareStateJson();
        //main_1(args);
        //testOptionalMap();
        testEmptyJson();
    }

    public static Optional<Integer> getIntOptional(){
        return Optional.empty();
    }

    public static State getState(){
        State state = new State();
        state.member1 = 89;
        state.member2 = "some random";
        return state;
    }

    public static void testEmptyJson() throws IOException {
        State st = getState();
        JsonNode jsonN = objectMapper.convertValue(st, JsonNode.class);
        System.out.println(jsonN);

        objectMapper.writeValue(new File("mandrill.json"), st);
        State st2 = objectMapper.convertValue(jsonN, State.class);
        System.out.println(st2.jsonNode);
    }
    public static void testOptionalMap(){
        Integer result =  getIntOptional().map(integer -> integer * integer).get();
        System.out.println(result);
    }
    public static void testCompareStateJson() throws IOException {
        JsonNode currState = objectMapper.readValue("[[{\"id\":{\"accountId\":\"18ce53yzory\",\"entityId\":\"3pgfps\"},\"tableName\":\"PROMOTED_TWEET_REPORT\"},\"2020-04-05T22:57:23.206Z\"], [{\"id\":{\"accountId\":\"18ce53yzory\",\"entityId\":\"3pgfr5\"},\"tableName\":\"PROMOTED_TWEET_REPORT\"},\"2020-04-05T22:57:30.358Z\"]]", JsonNode.class);
        JsonNode persistedState = objectMapper.readValue("[[{\"id\":{\"accountId\":\"18ce53yzory\",\"entityId\":\"3pgfr5\"},\"tableName\":\"PROMOTED_TWEET_REPORT\"},\"2020-04-05T22:57:30.358Z\"], [{\"id\":{\"accountId\":\"18ce53yzory\",\"entityId\":\"3pgfps\"},\"tableName\":\"PROMOTED_TWEET_REPORT\"},\"2020-04-05T22:57:23.206Z\"]]", JsonNode.class);
//        assertEquals(currState, persistedState);
        if (currState.equals(persistedState))
            System.out.println("States are equal");
        else
            System.err.println("States are not equal");
    }
}
