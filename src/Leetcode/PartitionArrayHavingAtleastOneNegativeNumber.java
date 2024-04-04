package Leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/discuss/interview-question/4574669/Google-or-Onsite-or-Find-partitions
public class PartitionArrayHavingAtleastOneNegativeNumber {
    public static void main(String[] args) {
        int[] input = new int[]{-4,2,-3,4,1,-2,3};

        PartitionArrayHavingAtleastOneNegativeNumber p = new PartitionArrayHavingAtleastOneNegativeNumber();
        p.partitionDriver(input);
    }

    void partitionDriver(int[] arr){
        List<Integer> partial = new ArrayList<>();
        for(int i=0;i< arr.length;i++){
            partial.add(arr[i]);
            if(arr[i]<0){
                List<List<Integer>> r = partitionArray(partial,arr, arr.length-i);
            }
        }
    }

    List<List<Integer>> partitionArray(List<Integer> partial, int[] arr, int size){
        List<List<Integer>> res = new ArrayList<>();
        for(int i= arr.length - size;i< arr.length;i++){
            if(arr[i]<0){
                partial.add(arr[i]);
                List<List<Integer>> r = partitionArray(partial,arr, arr.length-i);
                System.out.println(r);
            }
        }

        return res;
    }

}
