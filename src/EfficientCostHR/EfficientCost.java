package EfficientCostHR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EfficientCost {

    public static void main(String[] args) {
        int[] input = {1, 5, 5,1};
        int len =input.length;
        int[][] memo = new int[len][len];
        for(int i =0 ;i< len;i++){
            for (int j = 0; j < len ; j++) {
                memo[i][j] = -1;
            }
        }

        HashMap<Integer, String> map = new HashMap<>();
        map.put(5, "V");
        map.put(3, "III");
        map.put(11, "XI");
        System.out.println(map);

        HashSet<String> set = new HashSet<>();

        set.add("VI");set.add("II");
        set.add("XII"); set.add("IX");
        set.add("II");

        System.out.println(set);
        //int finalCost = maxEfficientCost(input,0,2, memo);
        //System.out.println("Result " + finalCost);
    }

    private static Integer minInList(List<Integer> list){
        Integer minValue = Integer.MAX_VALUE;
        for (Integer item: list) {
            minValue = Math.min(minValue, item);
        }
        return minValue;
    }
    private static int maxInSubArray(int[] arr, int start, int k){
        int max = Integer.MIN_VALUE;
        for (int i = start; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    Runnable printHello(){
        return ()-> System.out.println("hello");
    }
    // 1,3,4,5,2,6
    private static int maxEfficientCost(int[] arr, int start, int k, int[][] memo){
        // if last k elements, get the max among it and return.
        if(start + 1 == arr.length) return arr[start];

        if(start + k >= arr.length )
            return maxInSubArray(arr, start, k);


        int localMax = arr[start];
        int localMin = Integer.MAX_VALUE;

        List<Integer> sums = new ArrayList<>();
        for(int i = start ; i < arr.length && i < start + k; i++){
            localMax = Math.max( arr[i], localMax);
            int local = maxEfficientCost(arr, start + 1, k, memo);
            sums.add(localMax+ local);
        }

        Integer item = minInList(sums);
        return  item;
    }
}
