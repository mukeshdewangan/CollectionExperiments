package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] input = {4,15,13,20,19,23, 20, 21,22};
        lis.getLongestIncreasingSubsequence(input);
    }

    public List<Integer> getLongestIncreasingSubsequence(int[] array){
        List<Integer> result = new ArrayList<>();
        int[][] memo = new int[array.length+1][array.length+1];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }

        System.out.println(lisInternalFrom(array, 0, -1, memo));
        return result;
    }

    private int lisInternalFrom(int[] array, int index, int prevIndex, int[][] memo){
        if(index >= array.length) return 0;
        // function - f(index, prev_index)

        if(memo[index][prevIndex+1] != -1) return memo[index][prevIndex+1];

        // NOT TAKE -> current index
        int lenNotTake = lisInternalFrom(array, index+1, prevIndex, memo);

        int lenTakeIt = 0;
        // TAKE IT -> we can take only if index is greater than prevIndex value
        if(prevIndex == -1 || array[prevIndex]< array[index]) {
            lenTakeIt = lisInternalFrom(array, index + 1, index, memo) + 1;
        }
        memo[index][prevIndex+1] = Math.max(lenTakeIt, lenNotTake);
        return memo[index][prevIndex+1];
    }

}
