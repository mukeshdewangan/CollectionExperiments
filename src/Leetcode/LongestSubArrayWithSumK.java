package Leetcode;

import java.util.*;

//https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
public class LongestSubArrayWithSumK {
    public static void main(String[] args) {
        //int[] arr = {0,9,-1,0,0,1,-1,0,1,9,0,9,1};
        //int[] arr = {1,9,4,2,3,1,5,1};
        int[] arr = {1,9,4,2,3,1,1,2,1,5,1};
        long target = 10;
        LongestSubArrayWithSumK a = new LongestSubArrayWithSumK();
        //System.out.println(a.getLongestSubarrayWithOutList(arr, target));
        //System.out.println(a.getLongestSubarrayWithList(arr, target));
        System.out.println(a.getLongestSubarrayWithTwoPointer(arr, target));
    }
    int getLongestSubarrayWithList(int[] arr, long k) {
        Map<Long, List<Integer>> prefixSumIndex = new LinkedHashMap<>();

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            List<Integer> indices = prefixSumIndex.getOrDefault(sum, new ArrayList<>());
            indices.add(i);
            prefixSumIndex.put(sum,indices);

        }
        //Map<>
        //0,1,2,3, 4, 5
        //2,4,5,9,12,15

        int maxLen = 0;
        for(Map.Entry<Long,List<Integer>> entry: prefixSumIndex.entrySet()){
            if(entry.getKey() == k){
                List<Integer> list = entry.getValue();
                maxLen = Math.max(maxLen, list.get(list.size()-1) + 1);
            }
            if(prefixSumIndex.containsKey(entry.getKey() - k)){
                int currentIndex = entry.getValue().get(0);
                List<Integer> complementIndices = prefixSumIndex.get(entry.getKey() - k);
                int nextIndexInitial = complementIndices.get(0);
                int nextIndex;
                if(currentIndex < nextIndexInitial){
                    nextIndex = complementIndices.get(complementIndices.size()-1);
                }
                else{
                    currentIndex = entry.getValue().get(entry.getValue().size()-1);
                    nextIndex = nextIndexInitial;
                }
                int diff = Math.abs(nextIndex - currentIndex);
                maxLen = Math.max(diff, maxLen);
            }
        }
        return maxLen;
    }


    int getLongestSubarrayWithOutList(int[] arr, long k) {
        Map<Long, Integer> prefixSumIndex = new LinkedHashMap<>();

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(!prefixSumIndex.containsKey(sum)){
                prefixSumIndex.put(sum,i);
            }
        }

        int maxLen = 0;
        for(Map.Entry<Long,Integer> entry: prefixSumIndex.entrySet()){
            if(entry.getKey() == k){
                maxLen = Math.max(maxLen, entry.getValue()+1);
            }
            if(prefixSumIndex.containsKey(entry.getKey() - k)){
                int currentIndex = entry.getValue();
                int nextIndex = prefixSumIndex.get(entry.getKey() - k);
                int diff = Math.abs(nextIndex - currentIndex);
                maxLen = Math.max(diff, maxLen);
            }
        }
        return maxLen;
    }

    int getLongestSubarrayWithTwoPointer(int[] arr, long k) {
        long sum = 0;
        int arrSize = arr.length;
        int left =0;
        int right = 0;
        int maxSize = 0;
        while (left< arrSize && right< arrSize){
            sum += arr[right++];
            if(sum == k){
                maxSize = Math.max(maxSize, (right - left) );
                //left++;
            }
            while(sum >= k){
                // 9,2,3,4,1,3,8
                // 9,

                sum = sum - arr[left] < 0 ? 0 : sum - arr[left];
                left++;
            }
        }
        return maxSize;
    }

}
