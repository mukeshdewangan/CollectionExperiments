package Leetcode;

// https://leetcode.com/contest/weekly-contest-393/problems/maximum-prime-difference/
public class MaximumDistancePrimeContest393 {
    public static void main(String[] args) {
        MaximumDistancePrimeContest393 mp = new MaximumDistancePrimeContest393();
        int[] nums = {4,2,9,5,3,17,91,93,100,102};
        System.out.println(mp.maximumPrimeDifference(nums));
        nums = new int[] {4,8,3,2,8};
        System.out.println(mp.maximumPrimeDifference(nums));
        nums = new int[] {1,7};
        System.out.println(mp.maximumPrimeDifference(nums));
    }

    public int maximumPrimeDifference(int[] nums) {
        int start =0; int end = nums.length-1;
        int result = 0;
        while(start<=end){
            boolean isStartPrime = false;
            while(start <=end && start <= nums.length-1){
                isStartPrime= isPrime(nums[start]);
                if(!isStartPrime ) start++;
                else break;
            }
            boolean isEndPrime = false;
            while(start<= end && end >= 0){
                isEndPrime = isPrime(nums[end]);
                if(!isEndPrime) end--;
                else break;
            }

            if(isStartPrime && isEndPrime){
                result = end-start;
                return result;
            }
        }
        return result;
    }

    private boolean isPrime(int num){
        if(num==1) return false;
        if(num<=3) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
}
