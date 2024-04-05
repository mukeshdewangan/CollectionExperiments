package Leetcode;

import java.util.*;

public class CreateBiggestNumberFromAGivenListOfIntegers {
    public static void main(String[] args) {
        //int[] strings1 = {3,5,73,13,9,12};
        //int[] strings1 = {3,9,12};
        //int[] strings1 = {90,9,1,72};
        //int[] strings1 = {1, 34, 3, 98, 9, 76, 45, 4};  // output 998764453341  //expected 998764543431
        //String[] strings = new String[4]; //{90,9,1,72}; // 990721
        int[] strings1 = {90,9,1,72};

        //Arrays.sort(strings, Comparator.reverseOrder());
        //System.out.println(Arrays.toString(strings));
        String out = getBiggestNumberFromNums(strings1);
        System.out.printf(out);
    }


    static String getBiggestNumberFromNums(int[] nums){
        String[] arr = new String[nums.length];
        for(int i=0;i<arr.length;i++){
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, new NumberComparator());
        StringBuilder builder = new StringBuilder();
        for(int i=arr.length -1; i>=0 ; i--){
            builder.append(arr[i]);
        }
        String result = builder.toString();
        return result;
    }
    static class NumberComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            String o1o2 = o1+o2;
            String o2o1 = o2+o1;
            return (o1o2.compareTo(o2o1));
        }
    }
}
