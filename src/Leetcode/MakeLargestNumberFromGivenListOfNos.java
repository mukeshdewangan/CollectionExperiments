package Leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MakeLargestNumberFromGivenListOfNos {

    private String largestNuber(String[] nums){
        List<String> arrNums = Arrays.asList(nums);
        Collections.sort(arrNums);
        for (int i = nums.length -1 ; i >= 0; i--) {
            System.out.println(arrNums.get(i));
        }

        return "";
    }
    public static void main(String[] args) {
        String[] nums = {"9","990", "997", "770", "77","5"};
        MakeLargestNumberFromGivenListOfNos m = new MakeLargestNumberFromGivenListOfNos();
        m.largestNuber(nums);
    }
}
