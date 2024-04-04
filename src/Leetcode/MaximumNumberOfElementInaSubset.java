package Leetcode;

// Find the Maximum Number of Elements in Subset
// https://leetcode.com/contest/weekly-contest-382/problems/find-the-maximum-number-of-elements-in-subset/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of positive integers nums.
 *
 * You need to select a subset of nums which satisfies the following condition:
 *
 * You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
 * Return the maximum number of elements in a subset that satisfies these conditions.
 */
public class MaximumNumberOfElementInaSubset {
    public static void main(String[] args) {
        MaximumNumberOfElementInaSubset m = new MaximumNumberOfElementInaSubset();
        //int[] nums = {5,81,4,1,2,2,1,81,3,3,9,9};
        //int[] nums = {1,1,1,1,1,1,1,1};
        //int[] nums = {5,81,4,1,2,2,1,81,3,3,9,9};
        //int[] nums ={7744,3600,4356,4900,4225,3481,3364,576,7921,1}
        //int[] nums = {5,81,4,2,2,4,16,16,64,64,1,1,81,3,3,9,9,6561};
        //int[] nums = {68,68,4624,4624,21381376,21381376};
        int[] nums = {4,36,9,16,1,1,4,121,64,4};
        //1,4,16,4,1
        System.out.println(m.maximumLength(nums));
    }
    public int maximumLength(int[] nums) {
        // Calculate the freq of each element
        int max = 0;
        for(int i=0; i< nums.length;i++){

            max = Math.max(max, nums[i]);
        }

        // int[] freqArr = new int[max+1];
        Map<Integer,Integer> freqArr = new HashMap<>();
        for(int i=0; i< nums.length;i++){
            //freqArr[nums[i]]++;
            freqArr.put(nums[i], freqArr.getOrDefault(nums[i],0)+1);
        }

        //int[] memoized = new int[max+1];
        Map<Integer, Integer> memoized = new HashMap<>();
        //Arrays.fill(memoized, -1);
        for (Map.Entry<Integer, Integer> entry : freqArr.entrySet()) {
            memoized.put(entry.getKey(),-1);
        }

        int maxLen = 1;
        //for(int i=2;i<=max;i++) {
        for (Map.Entry<Integer, Integer> entry: freqArr.entrySet()) {

            int i = entry.getKey();
            if(i==1)
                continue;
            // find the elements with minimum count 2
            int curLen  = maxLength(i, freqArr, max, memoized);
            maxLen = Math.max(curLen, maxLen);
            // For each such number calculate the max length returned for it
        }

        int freq1 = freqArr.getOrDefault(1, 0);
        int combinationOfOne =  freq1%2==0 ? freq1-1 : freq1;
        // Case of 1
//        if(max > 1 && freqArr.getOrDefault(1,0)>=2) {
//            maxLen += 2;
//        }
        maxLen = Math.max(maxLen, combinationOfOne);
        return maxLen;
    }

    //private int maxLength( int currentNum, int[] freqArray, int max, int[] memoized){
    private int maxLength( int currentNum, Map<Integer, Integer> freqArray, int max, Map<Integer, Integer> memoized ){
        int maxLength =1;
        if(memoized.get(currentNum) == null){
            return 0;
        }
        if(freqArray.get(currentNum) <= 1){
            memoized.put(currentNum, freqArray.get(currentNum));
            return memoized.get(currentNum);
        }

        if(freqArray.getOrDefault(currentNum,0) >= 2){
            int nextIndex = (int) Math.pow(currentNum, 2);
            if(Math.pow(currentNum, 2) <= max) {
                int len = maxLength(nextIndex, freqArray, max, memoized);
                if(len == 0){
                    maxLength = 1;
                }
                else if(len == 1){
                    maxLength = 3;
                }
                else if(len >= 2){
                    maxLength = len + 2;
                }
            }
        }

        memoized.put(currentNum,maxLength);
        return maxLength;
    }
}
