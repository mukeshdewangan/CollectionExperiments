package Leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] input = {0,0,1,1,1,1,2,3,3,3,4};

        int[] input2 = {0,1,1,1,1,2,3,3,3,4};
        int[] input3 = {0,1,2,3,4};
        int[] input4 = {4};

        int[] result= getCompressedArray(input);
        result= getCompressedArray(input2);
        result= getCompressedArray(input3);
        result= getCompressedArray(input4);
    }

    private static int[] getCompressedArray(int[] input){
        int[] arr  = input;
        int k = 1;
        for(int i=1;i< arr.length;i++){
            if(arr[i] != arr[k-1]){
                arr[k++] = arr[i];
            }
        }
        System.out.println("k " + k);
        return arr;

    }
}
