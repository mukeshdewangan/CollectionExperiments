package Leetcode;

import java.util.Arrays;

// https://www.hackerrank.com/x/library/hackerrank/all/questions/287481/try
public class MovieRating {
    public static void main(String[] args) {
        //int[] arr = new int[] {-30,-17,8,-26,-31,-45,-23,-22,-16,-48,-35,-38,-20,-24,2,-40,-41,-43,6,-13,-5,-31,-47,-22,-13,2,-3,-19,-34,2,-15,-5,-26,-10,-22,-15,0,-1,-37,-46,1,-45,-37,4,-19,-22,-20,-41,-28,4};
        //int[] arr = new int[] {-1,-2,-3,-4,-5};
        //int[] arr = new int[] {-3,2,4,-1,-3,-5,-2};
        int[] arr = new int[] {5,-3,2,4,-4,-2};
        //int[] arr = new int[] {-6,4,6,-4,8,3,6,-9,8,-5};
        int sum = maximizeRatings(arr);
        //int sum= rob(arr);

        //System.out.println("sum " + sum + " for "+ Arrays.toString(arr));
        //arr = new int[] {9,-1,-3,4,5};
        //maximizeRatings( arr);
    }
    static int maximizeRatings(int[] ratings) {
        int[] memo = new int[ratings.length+1];
        Arrays.fill(memo, Integer.MIN_VALUE);
        memo[0] = ratings[0];
        if(ratings.length > 1) memo[1]= ratings[1];

        int sum = helper3(0,0,true, ratings, memo);
        System.out.println("sum " + sum + " for "+ Arrays.toString(ratings));
        return sum;
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 2; i < nums.length; i++) {
            int val = nums[i];
            memo[i+1] = Math.max(memo[i-2]+val, memo[i-1] + val);
        }
        return memo[nums.length];
    }

    static int helper2( int[] ratings, int[] memo, int currentIndex){
        if(currentIndex<0) return 0;
        if (memo[currentIndex] != Integer.MIN_VALUE) {
            return memo[currentIndex];
        }

        int result = Math.max(helper2(ratings, memo,currentIndex - 2) + ratings[currentIndex],
                helper2(ratings, memo, currentIndex - 1));
        memo[currentIndex] = result;
        return result;
    }

    // It works but TLE
    static int helper(int currentSum, int currentIndex, boolean isLastIncluded, int[] ratings, int[] memo){
        if(currentIndex >= ratings.length)
            return currentSum;

        int sumIncluded = helper(currentSum + ratings[currentIndex], currentIndex+1,true,  ratings, memo);
        int sumExcluded = helper(currentSum, currentIndex+1,false, ratings, memo);
        if(!isLastIncluded) {
            return sumIncluded;
        }

        //memo[currentIndex] = Math.max(sumIncluded, sumExcluded);
        return Math.max(sumIncluded, sumExcluded);
    }

    static int helper3(int currentSum, int currentIndex, boolean isLastIncluded, int[] ratings, int[] memo){
        if(currentIndex >= ratings.length)
            return currentSum;

        if(memo[currentIndex] != Integer.MIN_VALUE) {
            int sumIncluded = helper(currentSum + ratings[currentIndex], currentIndex + 1, true, ratings, memo);
            int sumExcluded = helper(currentSum, currentIndex + 1, false, ratings, memo);
            if (!isLastIncluded) {
                memo[currentIndex] = sumIncluded;
                return sumIncluded;
            }

            memo[currentIndex] = Math.max(sumIncluded, sumExcluded);
            return  memo[currentIndex];//Math.max(sumIncluded, sumExcluded);
        }
        else
            return memo[currentIndex];
    }
}
