package Leetcode;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
public class KPairsWithSmallestSums {
    public static void main(String[] args) {
        int[] nums1 = new int[] {1,7,11};
        int[] nums2 = new int[] {2,4,6};
        int k = 3;
        KPairsWithSmallestSums kp = new KPairsWithSmallestSums();
        List<List<Integer>> result = kp.kSmallestPairs(nums1, nums2,k);
        for (List<Integer> elem: result)
            System.out.println(elem.get(0) + "," + elem.get(1));
    }


    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((list1, list2)-> {
            long sum1 = list1.get(0)+list1.get(1);
            long sum2 = list2.get(0)+list2.get(1);
            if (sum1 == sum2){
                return 0;
            } else if (sum1 < sum2){
                return 1;
            } else{
                return -1;
            }
        });
        for (int elem1 : nums1) {
            for (int elem2 : nums2) {
                List<Integer> newItem = new ArrayList<>(List.of(elem1, elem2));
                pq.add(newItem);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while (!pq.isEmpty()){
            result.add(0,pq.poll());
        }
        return result;
    }

    // Two Pointer
    // [1,7,11]
    // [2,4,6]

    ///1-2
    //  1+4 = secondIncrementedSum = 5 ,  firstIncrementedSum 2+7 =9

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int totalCount = 0;
        int firstPtr = 0;
        int secondPtr = 0;

        result.add(new ArrayList<>(List.of(nums1[firstPtr], nums2[secondPtr])));
        //long currentSum = nums1[firstPtr] + nums2[secondPtr];
        while(totalCount < k && firstPtr < nums1.length && secondPtr < nums2.length){
            // try with firstPtr and secondPtr+1
            long secondIncrementedSum = Long.MAX_VALUE;
            long firstIncrementedSum = Long.MAX_VALUE;
            if(secondPtr+1 < nums2.length) {
                secondIncrementedSum = nums1[firstPtr] + nums2[secondPtr+1];
                if(firstPtr+1 < nums1.length) {
                    firstIncrementedSum = nums1[firstPtr + 1] + nums2[secondPtr];
                    if(secondIncrementedSum < firstIncrementedSum) {
                        result.add(new ArrayList<>(List.of(nums1[firstPtr], nums2[secondPtr+1])));
                        secondPtr++;
                    }
                    else{
                        result.add(new ArrayList<>(List.of(nums1[firstPtr+1], nums2[secondPtr])));
                        firstPtr++;
                    }
                }
                else {
                    result.add(new ArrayList<>(List.of(nums1[firstPtr], nums2[secondPtr+1])));
                    secondPtr++;
                }
            }
            if(firstPtr+1 < nums1.length) {
                firstIncrementedSum = nums1[firstPtr + 1] + nums2[secondPtr];
                if(secondPtr+1 < nums2.length) {
                    secondIncrementedSum = nums1[firstPtr + 1] + nums2[secondPtr];
                    if(secondIncrementedSum < firstIncrementedSum) {
                        result.add(new ArrayList<>(List.of(nums1[firstPtr], nums2[secondPtr+1])));
                        secondPtr++;
                    }
                    else{
                        result.add(new ArrayList<>(List.of(nums1[firstPtr+1], nums2[secondPtr])));
                        firstPtr++;
                    }
                }
                else{
                    result.add(new ArrayList<>(List.of(nums1[firstPtr+1], nums2[secondPtr])));
                    firstPtr++;
                }
            }
            totalCount++;
        }

        return result;
    }
}
