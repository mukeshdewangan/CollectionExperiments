package Leetcode;
//
// https://leetcode.com/problems/non-decreasing-array/
public class NonDecreasingArrayWithOneModification {
    public static void main(String[] args) {
        int[] input = new int[] {4,2,1};

        boolean res = checkPossibility(input);
        System.out.println(res);
    }
    public static boolean checkPossibility(int[] nums) {
        int len = nums.length;
        int currentMax = nums[0];

        int count =0;
        for(int i = 1; i<= len - 1 ;i++){
            if (nums[i] >= currentMax) currentMax = nums[i];
            else count++;
        }
        if(count <= 1) return true;

        count = 0;
        currentMax = nums[len-1];

        for(int i = len-2; i>=0 ; i--){
            if( currentMax >= nums[i]) currentMax = nums[i];
            else count++;
        }

        return count <= 1;
    }
}
