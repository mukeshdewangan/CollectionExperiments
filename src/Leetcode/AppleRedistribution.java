package Leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppleRedistribution {
    public static void main(String[] args) {
        AppleRedistribution a = new AppleRedistribution();
        int[] apple = {1,3,2};
        int[] capacity = {4,3,1,5,2};
        System.out.println(a.minimumBoxes(apple,capacity));

        apple = new int[]{5, 5, 5};
        capacity = new int[]{2, 4, 2, 7};
        System.out.println(a.minimumBoxes(apple,capacity));

    }
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        for (int a : apple) {
            sum+=a;
        }
        List<Integer> capList = new java.util.ArrayList<>(Arrays.stream(capacity)
                .boxed()
                .toList());

        capList.sort(Collections.reverseOrder());

        int[] prefixSum = new int[capacity.length];

        prefixSum[0] = capList.get(0);
        for(int i=1;i<capList.size();i++){
            prefixSum[i] = prefixSum[i-1] + capList.get(i);
            //System.out.println(capList.get(i));
        }
        int i =0;
        while(sum > prefixSum[i]){
            i++;
        }
        return i+1;
    }
}
