package Leetcode;

//https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
public class MaxProductDifference {
    public static void main(String[] args) {
        MaxProductDifference md = new MaxProductDifference();
        System.out.println(md.maxProductDiff( new int[]{5,6,2,8,4,9}));
    }
    public int maxProductDiff(int[] nums) {
        // min2, min1,..... max1, max2
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int i =0; i< nums.length;i++){
            int numi = nums[i];

            if(numi < min1 && numi < min2) { min1 = min2; min2 = numi;}
            else if(numi < min1) { min1 = numi;}

            if(numi > max1 && numi > max2) { max1 = max2; max2 = numi;  }
            else if(numi > max1) { max1 = numi;}

        }
        int diff = max1*max2 - min1*min2;
        return diff;
    }
}
