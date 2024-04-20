package Leetcode;

import java.util.Arrays;
import java.util.Collections;

// https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k/description/
public class MinimumOperationToMakeMedianK {
    public static void main(String[] args) {
        MinimumOperationToMakeMedianK mk = new MinimumOperationToMakeMedianK();

        int[] nums = {2,5,6,8,5};
        int k=4;
        System.out.println(mk.minOperationsToMakeMedianK(nums,k));

        k=7;
        System.out.println(mk.minOperationsToMakeMedianK(nums,k));

        nums = new int[]{1, 2, 3, 4, 5, 6};
        k = 4;
        System.out.println(mk.minOperationsToMakeMedianK(nums,k));
    }

    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);

        int mid =  (int)(Math.ceil(nums.length/2.0));

        // For first part, i.e.  elements before k
        for (int i = mid-1; i >= 0; i--) {
            //if()
        }

        // for second part, i.e. elements after k
        for (int i = mid; i < nums.length; i++) {

        }
        return nums[mid];
    }

}
