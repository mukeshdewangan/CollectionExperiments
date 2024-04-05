package Leetcode;

public class MinimumSubArrayLengthSumDivisiableByK {

    static int minimumLength(int[] array, int k){

        int sum = 0 ;
        for (int i = 1; i <= array.length ; i++) { // iterate for every length
            for(int j=0; j <= array.length - i; j++){ // iterate for every starting point
                int intermediateSum = 0;
                for(int l = j; l < j+i ;l++){
                    intermediateSum+= array[l];
                }
                if(intermediateSum % k== 0)
                    return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] input = {1,3,3};
        int k = 6;
        System.out.println(minimumLength(input,6));
    }
}
