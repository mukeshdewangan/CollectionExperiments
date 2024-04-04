package Leetcode;

/**
 * A bitonic sequence is a sequence of numbers that is first non-decreasing, and then non-increasing.
 * Given an array of integers arr, find the length of the longest subarray which is bitonic in nature.
 * Example
 * arr = [10, 8, 9, 15, 12, 6, 7]
 * [8, 9, 15, 12, 6] is the longest bitonic subarray. Return its length, 5.
 * Constraints
 * 1 ≤ n ≤ 10^5
 * 1 ≤ arr[i] ≤ 10^9
 */
public class LongestBitonicArray {
    public static void main(String[] args) {

    }


//    def longestBitonicSubarray(arr):
//    ans = 0
//    inc = [1] * len(arr)
//    dec = [1] * len(arr)
//
//    for i in range(1,len(arr)):
//            if arr[i] >= arr[i-1]:
//    inc[i] += inc[i-1]
//
//            for i in range(len(arr)-2,-1,-1):
//            if arr[i] >= arr[i+1]:
//    dec[i] += dec[i+1]
//
//            for i in range(0,len(arr)):
//    ans = max(ans, inc[i] + dec[i] - 1)
//
//    return ans
}
