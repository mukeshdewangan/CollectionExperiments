package Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MergeInterval {
    public static void main(String[] args) {
        int[][] intervals = {{2,3},{2,2},{8,10},{15,18}};
        MergeInterval mergeInterval = new MergeInterval();
        int[][] resultIntervals = mergeInterval.merge(intervals);
        for (int i = 0; i < resultIntervals.length; i++) {
            for (int j = 0; j < resultIntervals[i].length; j++) {
                System.out.println(resultIntervals[i][j]);
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        int[][] result = new int[intervals.length][2];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int finalSize = 0;
        int[] lastPair = new int[]{intervals[0][0], intervals[0][1]};
        for (int i = 1; i < intervals.length; i++) {
            int[] currentPair = intervals[i];
            if(currentPair[0] <= lastPair[1]){
                lastPair[1] = Math.max(lastPair[1], currentPair[1]);
            }
            else{
                result[finalSize++]= lastPair;
                lastPair = currentPair;
            }
        }
        result[finalSize++]= lastPair;
        return result;
    }
}
