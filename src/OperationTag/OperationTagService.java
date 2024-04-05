package OperationTag;


import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class OperationTagService {

    static class Obj{
        public String name;
        Obj(String n){
            this.name = n;
        }
    }
    public Map<OperationType, Set<String>> operationsAllowedTags = new HashMap<>();
    Map<EnumSet<OperationType>, Long> sizes = new HashMap<>();
    private static String unsupportedOperation = "%s method is not implemented";
    public enum OperationType {
        UPSERT_LIVE_MODE,
        UPSERT_HISTORY_MODE,
        UPDATE_LIVE_MODE,
        UPDATE_HISTORY_MODE,
        DELETE_LIVE_MODE,
        DELETE_HISTORY_MODE
    }

    void registerOperationTags( OperationType op, String... tags){
        operationsAllowedTags.remove(op);
        operationsAllowedTags.put(op, new HashSet<>(Arrays.asList(tags)));
    }

    static void OperationTags(String inputStr, OperationType... types){
        System.out.println("inputStr " + inputStr);
        for(OperationType o : types) {
            System.out.println(o);
        }
    }

    boolean isAllowedTag(OperationType op, String... tags){
        if(!operationsAllowedTags.containsKey(op))
            throw new RuntimeException( "tags "+ tags +" not defined for op "+ op);
        Set<String> allowedTags = operationsAllowedTags.get(op);

        Set<String> passedTags = new HashSet<>(Arrays.asList(tags));

        return allowedTags.size() >= passedTags.size() && allowedTags.containsAll(passedTags);
    }

    static OperationType[] takeStringTags(String... args){
        Set<String> setOfStringTags = Arrays
                .stream(args)
                .collect(Collectors.toSet()); // O(n)

        List<OperationType> arrOfTags = new ArrayList<>();

        for (String setOfStringTag : setOfStringTags) {
            String s = setOfStringTag.toUpperCase();
            try {
                arrOfTags.add(OperationType.valueOf(s));
            } catch (IllegalArgumentException e) {
                System.out.println("invalid tag " + s);
            }
        }

        OperationType[] a = new OperationType[arrOfTags.size()];
        return  arrOfTags.toArray(a);
    }


   void recordSizeForMetricLogging1(OperationType[] tags, Long size) {
       //long val = 161130039548l;
       // long val = 161174173730052082l;
       long val = 161130039548l;
       //long val = 1611741737l; //30052082l;

       //val = 161130039548l;

       if(val < -31557014167219200L || val > 31556889864403199L)
           throw new RuntimeException(" Invalid long value, Unable to convert to Instant " + val);
       Instant i = Instant.ofEpochMilli( val );
       System.out.println(i);
        if(tags.length == 0)
            sizes.merge(EnumSet.noneOf(OperationType.class) , size, Long::sum);
        else {
            EnumSet<OperationType> enumSet = EnumSet.copyOf( Arrays.asList( tags ) );

            sizes.merge( enumSet, // create EnumSet from array
                    size,
                    Long::sum );
        }
   }

  /* void recordSizeForMetricLogging(String[] tags, Long size) {

        sizes.merge(Arrays.asList(tags),
                size,
                Long::sum);
    }*/

    public static void main(String[] args) {


        System.out.println( Instant.now().getEpochSecond() );
        String s = String.format( unsupportedOperation , "hardDelete" );
        System.out.println( s);
        Obj a = new Obj("shyam");
        Obj b = new Obj("gopal");

        Obj[] arr = {a, b};
        List<Obj> newArr = Arrays.asList( arr );

        newArr.get( 0 ).name = "banshidhar";

        System.out.println(a.name);
        //String[] newStrArr = new String[]{"2", "3", "4"};
        //OperationType[] enumArr = ;
        //OperationTags("mukesh", OperationType.DELETE_HISTORY_MODE, OperationType.DELETE_LIVE_MODE);
        //OperationType op =  OperationType.valueOf("Delete_HISTORY_MODE");
        //System.out.println(op);
        //takeStringTags("DELETE_HISTORY_MODE", "UPSert_HISTORY_MODE","DELETE_LIVE_MODE", "DELETE_LIVE_MODE", "SOME_random_MODE", "UPDATE_LIVE_MODE", "DELETE_HISTORY_MODE");

        List<String> first = Arrays.asList( "INITIAL_SYNC", "MODIFIED" );
        List<String> second = Arrays.asList( "INCREMENTAL", "FINAL" );
        List<String> third = new ArrayList<>();

        String[] arr12 = {"INITIAL_SYNC", "MODIFIED"};
        String[] arr23 = {"INCREMENTAL", "FINAL"};

        OperationType[] arr1 = {OperationType.UPSERT_HISTORY_MODE,OperationType.UPDATE_HISTORY_MODE, OperationType.DELETE_HISTORY_MODE,  OperationType.DELETE_LIVE_MODE};
        OperationType[] arr2 = {OperationType.DELETE_HISTORY_MODE, OperationType.DELETE_LIVE_MODE,OperationType.UPSERT_HISTORY_MODE,OperationType.UPDATE_HISTORY_MODE};

        OperationType[] arr3 = {};

         Collections.emptyMap();

        OperationTagService tagService = new OperationTagService();
        tagService.recordSizeForMetricLogging1(arr1, 45000L);
        tagService.recordSizeForMetricLogging1(arr1, 24000L);
        tagService.recordSizeForMetricLogging1(arr2, 870L);
        tagService.recordSizeForMetricLogging1(arr2, 870L);
        tagService.recordSizeForMetricLogging1(arr2, 870L);
        tagService.recordSizeForMetricLogging1(arr3, 870L);

        System.out.println(tagService.sizes.get( first ));
        System.out.println(tagService.sizes.get( second ));
        System.out.println(tagService.sizes.get( third ));

        OperationType[] operationType= {OperationType.DELETE_HISTORY_MODE, OperationType.UPSERT_HISTORY_MODE, OperationType.UPDATE_LIVE_MODE};
        List<OperationType> types = Arrays.asList(operationType);
        System.out.println(types);
    }
}
