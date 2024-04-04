package Leetcode;

import java.util.*;

/// Maximum Size of a Set After Removals
//https://leetcode.com/contest/weekly-contest-379/problems/maximum-size-of-a-set-after-removals/
public class MaxSizeSetAfterRemovals {
    public static void main(String[] args) {
        int[] nums1= {1,1,2,2,3,3};
        int[] nums2 = {4,4,5,5,6,6};

        MaxSizeSetAfterRemovals mp = new MaxSizeSetAfterRemovals();
        int res = mp.maximumSetSize(nums1, nums2);
        System.out.println(res);
    }

    public int maximumSetSize(int[] nums1, int[] nums2) {
        Map<Integer,Integer> count1 = new HashMap<>();
        Map<Integer,Integer> count2 = new HashMap<>();

        for(int i: nums1){
            count1.put(i, count1.getOrDefault(i,0)+1);
        }
        for(int i: nums2){
            count2.put(i, count2.getOrDefault(i,0)+1);
        }

        int remaining1 = nums1.length/2;
        int remaining2 = nums1.length/2;

        Map<Integer,Integer > num1num2Common = new HashMap<>();

        for(Map.Entry<Integer, Integer> entry: count1.entrySet()){
            int key1 = entry.getKey();

            if(count2.containsKey(key1)){
                // reduce of the key1 in count2
                int value2 = count2.get(key1);
                if(remaining2 - value2 >=0){
                    count2.remove(key1);
                    remaining2 = remaining2 - value2;
                }
                else {
                    int remove2 = value2 - remaining2;
                    count2.put(key1, remove2);
                    remaining2 =0;
                    break;
                }
            }
            int value1 = count1.get(key1);
            if(remaining1 - (value1-1) >= 0){
                remaining1 = remaining1 - (value1-1);
                num1num2Common.put(key1,1);
            }
            else{
                num1num2Common.put(key1,(value1 - remaining1));
                remaining1 = 0;
                break;
            }
        }

        count1.putAll(num1num2Common);

        // iterate count1 and count2 if remaining1 and remaining2 are still positive
        if(remaining2 > 0) {
            for (Map.Entry<Integer, Integer> entry : count2.entrySet()) {
                int value2 = entry.getValue();
                int key2 = entry.getKey();
                if (remaining2 - (value2-1) >= 0) {
                    count2.put(key2, 1);
                    remaining2 = remaining2 - (value2 -1);
                } else {
                    remaining2 = 0;
                    break;
                }
            }
        }
        if(remaining1 > 0) {
            for (Map.Entry<Integer, Integer> entry : count1.entrySet()) {
                int value1 = entry.getValue();
                int key1 = entry.getKey();
                if (remaining1 - (value1-1) >= 0) {
                    count1.put(key1, 1);
                    remaining1 = remaining1 - (value1-1);
                } else {
                    remaining2 = 0;
                    break;
                }
            }
        }

        int totalCount=0;
        for (Map.Entry<Integer, Integer> entry : count1.entrySet()) {
            if(entry.getValue()>0) totalCount++;
        }

        for (Map.Entry<Integer, Integer> entry : count2.entrySet()) {
            if(entry.getValue()>0) totalCount++;
        }

        return totalCount;
    }
}
