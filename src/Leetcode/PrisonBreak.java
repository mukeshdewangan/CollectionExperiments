package Leetcode;

import java.util.Arrays;
import java.util.List;

public class PrisonBreak {
    private static int getMaxGap(int n, List<Integer> h){
        // count maximum consecutive elements

        int count = 1;
        int max = 1;
        Integer[] array = new Integer[h.size()];
        h.toArray(array);

        for(int i = 1; i < array.length; i++) {
            if(array[i].equals(array[i - 1] + 1))
                count++;
            else {
                max = Math.max(max, count);
                count=1;
            }
        }
        max = Math.max(max,count);
        return max+1;
    }
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        List<Integer> h = Arrays.asList(1,2,3,4,6,7);
        List<Integer> v = Arrays.asList(2);

        System.out.println(getMaxGap(n,h));
    }
}
