package Leetcode;

public class StrictlyIncreasingOrDecreasing {
    public static void main(String[] args) {
        int[] nums = new int[]{0,4,6,8,21,91,9,1,1,1,1,1,1};
        StrictlyIncreasingOrDecreasing st = new StrictlyIncreasingOrDecreasing();

        System.out.println( st.longestMonotonicSubarray(nums));

        nums = new int[]{3,3};
        System.out.println( st.longestMonotonicSubarray(nums));

        nums = new int[]{1,9,7,1};
        System.out.println( st.longestMonotonicSubarray(nums));
    }
    public int longestMonotonicSubarray(int[] nums) {
        int maxIncreasingSubArr = 1;
        int maxDecreasingSubArr = 1;

        int localIncrease = 1;
        int localDecrease =1;
        for (int i = 1; i < nums.length; i++) {
            // detect increasing
            if(nums[i] > nums[i-1]){
                localIncrease++;
                localDecrease =1;
            }
            // detect decreasing
            if(nums[i] < nums[i-1]) {
                localDecrease++;
                localIncrease = 1;
            }

            if(nums[i] == nums[i-1]){
                localIncrease = 1;
                localDecrease =1;
            }

            maxIncreasingSubArr = Math.max(localIncrease, maxIncreasingSubArr);
            maxDecreasingSubArr = Math.max(localDecrease, maxDecreasingSubArr);
        }
        return Math.max(maxDecreasingSubArr, maxIncreasingSubArr);
    }
}
