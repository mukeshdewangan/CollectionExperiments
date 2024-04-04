package Leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/duplicate-zeros/
public class DuplicateZeros {
    public static void main(String[] args) {
        DuplicateZeros d = new DuplicateZeros();
        //int[] nums = {0,0,0,1,4,0,1};
        //int[] nums = {0,0,0,4,0,0,0,0};
        int[] nums = {1,0,2,3,0,4,5,0};
        d.duplicateZeros(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void duplicateZeros(int[] arr) {
        int zeroCount = 0;
        for (int j : arr) {
            if (j == 0) zeroCount++;
        }
        // count from end , if 0 is encountered 2 x zeroCount-- else 1 x  zeroCount--
        int remainingIndex = arr.length-1;
        while(zeroCount>0){
            if( arr[remainingIndex] == 0){
                zeroCount--;
            }
            zeroCount--;
            remainingIndex--;
        }

        // if zeroCount == -1 , lastIndex =0 and then copy from remainingIndex
        int copyIndex = arr.length -1;
        if(zeroCount == -1){
            arr[copyIndex--] = 0;
        }
        while(copyIndex >=0){
            if(arr[remainingIndex] == 0){
                arr[copyIndex--]  = arr[remainingIndex];
            }
            arr[copyIndex--] = arr[remainingIndex--];
        }
    }
}
