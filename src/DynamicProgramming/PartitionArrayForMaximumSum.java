package DynamicProgramming;

import jnr.ffi.annotations.In;

import java.util.Arrays;

//https://leetcode.com/problems/partition-array-for-maximum-sum/description/
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        PartitionArrayForMaximumSum msum = new PartitionArrayForMaximumSum();
        int[] input = {1,15,7,9,2,5,10};
        int k = 3;
        int answer = msum.maxSumAfterPartitioning(input, k);
        System.out.println(answer);

        input = new int[]{ 1,4,1,5,7,3,6,1,9,9,3};
        k = 4;
        answer = msum.maxSumAfterPartitioning(input, k);
        System.out.println(answer);
        input = new int[]{ 1};
        k = 1;
        answer = msum.maxSumAfterPartitioning(input, k);
        System.out.println(answer);
    }
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] memo = new int[arr.length+1];
        Arrays.fill(memo, -1);
        int res = maxSumPartitionRecursive(arr, 0, k, memo);
        return res;
    }

    private int maxSumPartitionRecursive(int[] arr, int start, int k, int[]memo){
        if(start == arr.length) return 0;
        if(memo[start] != -1) return  memo[start];
        int currenMax = Integer.MIN_VALUE;

        int maxSum = 0;
        for (int i = 0; i < k && start+i < arr.length ; i++) {
            currenMax = Math.max(currenMax, arr[start + i]);
            int localSum = maxSumPartitionRecursive(arr, start+(i+1), k, memo) + currenMax*(i+1);
            maxSum = Math.max(maxSum, localSum);

        }
        memo[start] = maxSum;
        return memo[start];
    }
}
