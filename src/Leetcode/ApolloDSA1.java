package Leetcode;

import java.util.*;


public class ApolloDSA1 {

    public long minimumCapacityOfCar(List<List<Integer>> input, Integer maxTrip) {
        // Assuming inner list contains 3 elements always
        long[] prefixSum = new long[input.size()];
        long sum = 0;
        int index = 0;
        long maxSum = 0;
        for(List<Integer> ele: input ){
            long tempSum = ele.get(0)+ele.get(1)*2+ele.get(2)*3;
            maxSum = Math.max(tempSum, maxSum);
            sum += tempSum;
            prefixSum[index++] = sum;
            System.out.print(" " + sum);
        }
        System.out.println(" ");
        long idealCapacity = (long) Math.ceil( (double)sum /maxTrip);
        if(maxSum > idealCapacity) {
            idealCapacity = maxSum;
            return maxSum;
        }
        long minCapacity = greaterThanOrEqualTo(prefixSum, idealCapacity);
        return minCapacity;
    }

    // Binary search
    public long greaterThanOrEqualTo(long[] prefixSum, long num){
        if(prefixSum[prefixSum.length-1] < num) return -1;
        int start = 0;
        int end = prefixSum.length-1;
        int result = end;
        // 3, 8 ,11, 13
        // start = 0 end = 3 , num =  6  , mid = 1

        while(start<=end){
            int mid = start + (end-start)/2;
            if(prefixSum[mid] == num){
                result = mid;
                break;
            }
            if(num < prefixSum[mid]){
                result = mid;
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        return prefixSum[result];
    }
    private List<List<Integer>> getInput(){
        List<List<Integer>> input  = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(1);
        list1.add(0); // 4


        List<Integer> list2 = new ArrayList<>();
        list2.add(3); // S
        list2.add(1); // M
        list2.add(1); // L // 8

        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(1);
        list3.add(0); // 4

        List<Integer> list4 = new ArrayList<>();
        list4.add(2);
        list4.add(2);
        list4.add(1); // 9

        input.add(list1);
        input.add(list2);
        input.add(list3);
        input.add(list4);
        return input;

    }

//    public static void main(String[] args) {
//        ApolloDSA1 main = new ApolloDSA1();
//        long[] prefix = new long[]{4,6,9,10,18,22};
//
//        long num  = main.greaterThanOrEqualTo(prefix, 1);
//        System.out.println(num);
//    }
    public static void main (String[] args) {
        //System.out.print(Foo("hello"));
        ApolloDSA1 main = new ApolloDSA1();

        int maxTrip = 2;
        System.out.println("start");
        List<List<Integer>> input = main.getInput();
        long answer = main.minimumCapacityOfCar(input,  maxTrip);

        System.out.println("answer " + answer);
    }

}