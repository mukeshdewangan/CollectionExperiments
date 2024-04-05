package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class HashMapExperiment {

    public static void main(String[] args) {
        int[] intRandoms = new int[100];
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            intRandoms[i] = (int)(Math.random()*100);

            count.put(intRandoms[i], count.getOrDefault(intRandoms[i],0)+1);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(intRandoms[i]);
        }

        for (Map.Entry<Integer, Integer> entry: count.entrySet() ) {
            System.out.println( entry.getKey() + " : " + entry.getValue());
        }
    }
}
