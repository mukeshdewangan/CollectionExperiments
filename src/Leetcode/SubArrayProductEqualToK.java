package Leetcode;

import java.util.List;

// Given an array of integers, numbers, and an integer k,
// determine the total number of subarrays of numbers that have a product that is less than or equal to k.
//
public class SubArrayProductEqualToK {
    public static void main(String[] args) {
        List<Integer> nums = List.of(2,3,1,4,2,3);  int k = 12;
        //List<Integer> nums = List.of(2,3,1,4); int k = 5;
        //List<Integer> nums = List.of(5, 3, 2); int k = 16;

        System.out.println(countSubarrays(nums,k));
    }

    /*
     * Complete the 'countSubarrays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER k
     */

    public static long countSubarrays(List<Integer> numbers, int k) {
        // Write your code here
        int left = 0;
        int right =0;
        long product =1;
        long count = 0;
        while(right < numbers.size()){
            product *= (numbers.get(right));
            while(left<numbers.size() && product>=k){
                product = product/numbers.get(left);
                left++;
            }

            if(product<k) {
                count += 1 + (right - left);
            }
            right++;
        }
        return count;
    }
}
