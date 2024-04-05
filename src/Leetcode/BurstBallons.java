package Leetcode;

import java.util.*;

public class BurstBallons {
    public static void main(String[] args) {
        BurstBallons b = new BurstBallons();
        System.out.println(b.maxCoins(new int[]{9,76,64,21}));
    }
    public int maxCoins(int[] nums) {
        int start=0 ;
        int end = nums.length-1;

        if(nums.length==1) return nums[0];
        Queue<Integer> queue = new LinkedList<>();
        int currentProduct;
        int sum = 0;
        int itemA , itemB;
        if(nums[start] > nums[end]){
            itemA = nums[start];
            itemB = nums[end];
        }
        else{
            itemA = nums[end];
            itemB = nums[start];
        }
        currentProduct = itemA;
        queue.add(itemA);
        sum += currentProduct;
        currentProduct = currentProduct * itemB;
        queue.add(itemB);
        sum += currentProduct;
        start++; end--;
        List<Integer> addList = new ArrayList<>();
        //Arrays.sort(arr, Comparator.reverseOrder());

        while(end - start >= 0){
            int item;
            if(nums[start] > nums[end]){
                item = nums[start];
                start++;
            }
            else{
                item = nums[end];
                end--;
            }
            int popItem =1;
            if(queue.size() == 3){
                popItem = queue.remove();
            }
            currentProduct = currentProduct/popItem;
            currentProduct = currentProduct*item;
            sum += currentProduct;
            queue.add(item);
        }

        return sum;
    }
}
