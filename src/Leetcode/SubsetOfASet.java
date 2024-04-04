package Leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class SubsetOfASet {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(3,5,7));
        List<List<Integer>> subsets = getSubset(nums);
        printSubset(subsets);
    }

    private static List<List<Integer>> getSubset(List<Integer> numbers){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> emptySet = new ArrayList<>();
        result.add(emptySet);
        for(Integer num: numbers){
            subsetHelper(num, result);
        }
        return result;
    }

    private static void subsetHelper(Integer number, List<List<Integer>> existingSets){
        List<List<Integer>> tempSubSets = new ArrayList<>();
        for(List<Integer> set: existingSets ){
            List<Integer> newSet = new ArrayList<>(set);
            newSet.add(number);
            tempSubSets.add(newSet);
        }

        existingSets.addAll(tempSubSets);
    }

    private static void printSubset(List<List<Integer>> subSets){
        for (List<Integer> set: subSets) {
            System.out.print("[");
            for (Integer i : set ) {
                System.out.print(i+ " ");
            }
            System.out.print("]");
        }
    }
}
