package Leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/contest/weekly-contest-381/problems/count-the-number-of-houses-at-a-certain-distance-i/
public class MinimumKeyPushesLCContest381 {
    public static void main(String[] args) {
        MinimumKeyPushesLCContest381 lc = new MinimumKeyPushesLCContest381();
        //System.out.println(lc.minimumPushes("abcde"));
        System.out.println(lc.minimumPushes("xyzxyzxyzxyz"));
    }
    public int minimumPushes(String word) {
        Map<Character, Integer> charOcc = new HashMap<>();

        for (int i = 0; i < word.length() ; i++) {
            charOcc.put(word.charAt(i), charOcc.getOrDefault(word.charAt(i), 0)+1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a,b)-> b.getValue() - a.getValue());
        pq.addAll(charOcc.entrySet());
        //System.out.println(pq.size());
        int totalKeyPresses = 0;
        int distinctChar =0;
        while (!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            char ch = entry.getKey();
            int occ =  entry.getValue();

            int value = (distinctChar/8)+1;
            totalKeyPresses += calculateKeyPushes( value, occ );
            distinctChar++;
        }
        return totalKeyPresses;

    }
    public int calculateKeyPushes(int keyValue, int occurrences){
        return keyValue*occurrences;
    }
}
