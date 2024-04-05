package Leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

//https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
public class CountUniqueCharacters {
    public static void main(String[] args) {

        String str = "ABC";

        int[] arr1= new int[]{0,1,2,2,1};
        int[] arr2= new int[]{0,1,2,1,1};
        int[] arr3= new int[]{0,1,2,2,1};

        System.out.println(Arrays.equals(arr1, arr3));

    }

    //private static List<String>
    private static int countUniqueChars(String t, Map<Character, Integer> countUniques){
        int result = 0;
        for (Map.Entry<Character, Integer> entry : countUniques.entrySet()) {
            if(entry.getValue() == 1) result++;
        }
        return result;
    }
}
