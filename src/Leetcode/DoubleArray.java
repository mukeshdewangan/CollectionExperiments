package Leetcode;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
public class DoubleArray {
    public static void main(String[] args) {
        DoubleArray da = new DoubleArray();
        int[] input = new int[]{1,3,6,2,2,4};
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{2,4,8,2,4,4,8,16};
        //{2,4,2,4,4,8,8,16}
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{1,2,4,8,4,8,8,16,18,36,2,4,2,4};
        //{2,4,2,4,4,8,8,16}
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{3,2,4,6,2,4};
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{0,2,0,1};
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{1,2,1,0};
        System.out.println(Arrays.toString(da.findOriginalArray(input)));

        input = new int[]{5,7,2,10,4,2,7,14}; // 2,2,4,5,7,7,10,14

        System.out.println(Arrays.toString(da.findOriginalArray(input)));


    }
    public int[] findOriginalArray(int[] changed) {
        //List<Integer> emptyArr = new ArrayList<>();
        int[] empty= new int[]{};
        if(changed.length%2 != 0) return empty;
        Map<Integer, Integer> freq = new TreeMap<>();

        for(int i: changed){
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(freq.keySet());
        for(Integer key: keys){
            int doubleN = key*2;
            int occurrence = freq.getOrDefault(key,0);

            int doubleOccurrence = freq.getOrDefault(doubleN,0);
            if(doubleN == key ) {
                doubleOccurrence = doubleOccurrence/2;
            }
            if(occurrence > 0 && doubleOccurrence <= 0){
                return empty;
            }
            if(occurrence > 0) {
                if(doubleN == key ) {
                    occurrence = occurrence/2;
                }
                for (int i = 0; i < occurrence; i++) {
                    result.add(key);
                }

                freq.put(doubleN, freq.get(doubleN) - occurrence);
            }
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }

        return res;
    }


    // o_ert
}
