package Leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class PartitionNumsIntoTwoArrays {
    public static void main(String[] args) {

        int[] arrr ={1,2,14,15};
        int[] arr1 ={5,4,3,8};
        int[] arr2 ={2,1,3};
        PartitionNumsIntoTwoArrays p = new PartitionNumsIntoTwoArrays();
        System.out.println(Arrays.toString(p.resultArray(arrr)));
        System.out.println(Arrays.toString(p.resultArray(arr1)));
        System.out.println(Arrays.toString(p.resultArray(arr2)));

    }
    public int[] resultArray(int[] nums) {
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();

        result1.add(nums[0]);
        result2.add(nums[1]);
        for(int i =2;i < nums.length; i++) {
            if (result2.get(result2.size() - 1) > result1.get(result1.size() - 1)) {
                result2.add(nums[i]);
            } else
                result1.add(nums[i]);
        }
        int[] result = new int[nums.length];
        for(int i=0;i<result1.size();i++){
            result[i] = result1.get(i);
        }
        for(int i=0, idx = result1.size();i<result2.size();i++){
            result[idx++] = result2.get(i);
        }
        return result;
    }
}
