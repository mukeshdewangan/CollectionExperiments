package InternInterview;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, String> hm
                = new LinkedHashMap<>();

        // Inserting the Elements

        for (int i = 0; i < 3; i++) {
            hm.put(1 + i*10, "Geeks");
            hm.put(4 + i*10, "4Geeks");
            hm.put(7 + i*10, "7Geeks");
            hm.put(3 + i*10, "Geeks");
            hm.put(2+ i*10, "For");
        }

        for (Map.Entry<Integer, String> mapElement : hm.entrySet()) {

            Integer key = mapElement.getKey();

            // Finding the value
            String value = mapElement.getValue();

            // print the key : value pair
            System.out.println(key + " : " + value);
        }
    }
}
