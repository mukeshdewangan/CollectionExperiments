package Front;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverClassForDuplicateHistoryColumn {
    public static final String FIVETRAN_START = "_fivetran_start";
    public static final String FIVETRAN_END = "_fivetran_end";
    public static final String FIVETRAN_ACTIVE = "_fivetran_active";
    public static void main(String[] args) {
        String bigNumber = "179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000";


        String biggerNumber = "1797693134862315700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000";

        float toFloat = Float.parseFloat(bigNumber);
        System.out.println(toFloat);
        double toDouble = Double.parseDouble(bigNumber);
        System.out.println(toDouble);


        float toFloat2 = Float.parseFloat(biggerNumber);
        System.out.println(toFloat2);
        double toDouble2 = Double.parseDouble(biggerNumber);
        System.out.println(toDouble2);

        /*
        System.out.println("Event type null");
        EventType e = EventType.valueOf(null);
        System.out.println(e);

        Column col1 = new Column("id", "Integer", true);
        Column col2 = new Column("end", "String", false);
        Column col3 = new Column("start", "Instant", false);

        List <Column> list = new ArrayList<>();
        list.add(col1);
        list.add(col2);
        list.add(col3);

        boolean res = checkExistenceInTheList(list, "end");
        System.out.println(res);
        mapIteration();
         */
    }

    public static boolean checkExistenceInTheList(List<Column> list, String filterName){
        return (list.stream().anyMatch(o -> o.name.equals(filterName)));
    }

    static void mapIteration(){
        Map<String,Object>  map = new HashMap<>();
        map.put("id", "234234");
        map.put("bigdecimal", 239.2323783);
        map.put("asa",null);
        for (Map.Entry<String, Object> field: map.entrySet()) {
            System.out.println("key "+  field.getKey() + " value " + field.getValue());
        }
    }
}
