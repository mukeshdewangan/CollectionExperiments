package Leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/contest/weekly-contest-291/problems/minimum-consecutive-cards-to-pick-up/
public class RemoveConsecutiveCards {

    public static void main(String[] args) {
        int[] nums = {3,4,2,4,5,3,4,7};
        RemoveConsecutiveCards c = new RemoveConsecutiveCards();
        System.out.println(c.minimumCardPickup(nums));
    }
    public int minimumCardPickup(int[] cards) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        for(int i=0;i<cards.length;i++){
            if(lastOccurrence.containsKey(cards[i])){
                int lastIndex = lastOccurrence.get(cards[i]);
                int distance = i - lastIndex;
                lastOccurrence.put(cards[i], i);
                min = Math.min(distance, min);
            }
            else{
                lastOccurrence.put(cards[i], i);
            }
        }
        if(min==Integer.MAX_VALUE)  return -1;
        else return min+1;
    }
}
