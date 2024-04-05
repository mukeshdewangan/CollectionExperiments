package Leetcode;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-if-array-can-be-sorted/
public class CanSortArray {
    public static void main(String[] args) {
        //int[] nums = {8,4,2,30,15};
        //int[] nums = {1,2,3,4,5};
        //int[] nums = {3,16,8,4,2};
        //int[] nums = {136,256,10}; // 10001000, 100000000, 1010
        int[] nums = {174, 175, 234, 188};

        CanSortArray c = new CanSortArray();
        System.out.println("can sort " + c.canSortArray(nums));
    }

    public boolean canSortArray(int[] nums) {
        List<Integer> clone = new ArrayList<>(Arrays.stream(nums).boxed().toList());
        Collections.sort(clone);
        boolean allSorted = true;
        for (int i=0; i<nums.length;i++){
            if (clone.get(i) != nums[i]) {
                allSorted = false;
                break;
            }
        }
        if(allSorted) return true;

        Map<Integer, List<Integer>> bitCountMap = new LinkedHashMap<>();
        Set<Integer> bitCountSet = new HashSet<>();
        int currentBitCount = 0;
        //int nextBitCount;
        for(int i=0; i< nums.length; i++){
            int bitCount = numberOfBits(nums[i]);
            List<Integer> numbers = bitCountMap.getOrDefault(bitCount, new ArrayList<>());
            numbers.add(nums[i]);
            bitCountMap.put(bitCount, numbers);
            if(currentBitCount == 0){
                currentBitCount = bitCount;
                bitCountSet.add(currentBitCount);
            } else{
                if(bitCount != currentBitCount){
                    if(!bitCountSet.contains(bitCount)) {
                        bitCountSet.add(currentBitCount);
                        currentBitCount = bitCount;
                    }
                    else
                        return false;
                }
            }
            System.out.println(bitCount);
        }
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = bitCountMap.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> prev = iterator.next();
        List<Integer> list = prev.getValue();
        Collections.sort(list);
        while(iterator.hasNext()){
            Map.Entry<Integer, List<Integer>> nextItem = iterator.next();
            List<Integer> list2 = nextItem.getValue();
            // Sort first and second list
            Collections.sort(list2);
            int lastElementFromLast = prev.getValue().get(prev.getValue().size()-1);
            int firstElementFromNext = nextItem.getValue().get(0);
            if(firstElementFromNext < lastElementFromLast)
                return false;
            prev = nextItem;
        }
        return true;
    }

    public int numberOfBits(int n){
        int count =0;
        while(n > 0){
            n = n&n-1;
            count++;
        }
        return count;
    }
}
