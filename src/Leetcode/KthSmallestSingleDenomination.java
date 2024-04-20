package Leetcode;

import jnr.ffi.annotations.In;

import java.util.*;

// https://leetcode.com/contest/weekly-contest-393/problems/kth-smallest-amount-with-single-denomination-combination/
public class KthSmallestSingleDenomination {
    public static void main(String[] args) {
        KthSmallestSingleDenomination kth = new KthSmallestSingleDenomination();

        int[] coins = new int[]{8,9,5,2,3,4,6};
        int k = 4;
        long result;
         result = kth.findKthSmallest(coins, k);
        System.out.println( k + "th element is " + result);
        coins = new int[]{9,6,3};
        k = 9;
        result = kth.findKthSmallest(coins, k);
        System.out.println( k + "th element is " + result);

        coins = new int[]{2,5,10,17};
        k =56;
        int[] allMultiples = new int[]{2, 4, 5, 6, 8, 10, 12, 14, 15, 16, 17, 18, 20, 22, 24, 25, 26, 28, 30, 32, 34, 35, 36, 38, 40, 42, 44, 45, 46, 48, 50, 51, 52, 54, 55, 56, 58, 60, 62, 64, 65, 66, 68, 70, 72, 74, 75, 76, 78, 80, 82, 84, 85, 86, 88, 90, 92, 94, 95, 96, 98, 100};
        System.out.println( k+ " th " + allMultiples[k-1]);
        result = kth.findKthSmallest(coins, k);
        System.out.println( k + "th element is " + result);
    }
    public long findKthSmallest(int[] coins, int k) {
        List<Integer> removedMultiples = removeAllMultipleOfNum(coins);
        // Remove the factors
        for (Integer num:  removedMultiples) {
            System.out.println(num);
        }
        if(removedMultiples.size()==1){
            return (long)k*removedMultiples.get(0);
        }
        int maxCoin = removedMultiples.get(removedMultiples.size()-1);
        // Find the maximum and get all count of the number generated
        List<Integer> fewMultiples = findMultiplesTillMaxNum(removedMultiples);

        int fewMultiplesSize = fewMultiples.size();

        int multiple = (k-1)/fewMultiplesSize;

        long min = (long)multiple*maxCoin;
        long max = (long)(multiple+1)*maxCoin;
        List<Long> result = afterMin(removedMultiples, min,max);

        int index = (k-1) % fewMultiplesSize;
        //System.out.println(result.get(index));
        return result.get(index);
    }


    List<Long> afterMin(List<Integer> removedMultiples, long min, long max){
        Set<Long> result = new HashSet<>();

        for (Integer coin: removedMultiples ) {
            long multiple = (min/coin);
            while(multiple * coin <= max){
                long num = (multiple * coin);
                if(num!= 0 && num >= min){
                    result.add(multiple * coin);
                }
                multiple++;
            }
        }
        List<Long> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    List<Integer> findMultiplesTillMaxNum(List<Integer> coins){
        Set<Integer> res = new HashSet<>();
        int max = coins.get(coins.size()-1);
        for (Integer coin : coins) {
            int i = 1;
            while(coin*i <= max){
                res.add(coin*i);
                i++;
            }
        }
        List<Integer> tempRes = new ArrayList<>(res);
        Collections.sort(tempRes);
        return tempRes;
    }

    List<Integer> removeAllMultipleOfNum(int[] coins){
        Arrays.sort(coins);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            //System.out.println(coins[i]);
            int number = coins[i];
            boolean factor = false;
            for (int j = 0; j < i; j++) {
                if(number % coins[j] == 0) {
                    factor = true;
                    break;
                }
            }
            if(!factor)
                result.add(number);
        }
        return  result;
    }

    List<Long> generateBruteForce(List<Integer> coins, Long limit ){
        Set<Long> tempRes = new HashSet<>();
        for(Integer coin: coins){
            for (int i = 1; (long) i *coin <=limit ; i++) {
                tempRes.add((long)i *coin);
            }
        }
        List<Long> result = new ArrayList<>(tempRes);
        return result;
    }
}
