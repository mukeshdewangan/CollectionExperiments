package DynamicProgramming;

import java.util.*;

// Given a denomination of coins(each are infinitely available ) find the minimum coins need to make a Number N
// If not possible then return -1;
public class CoinChangeProblem {
    int[] computed;
    public static void main(String[] args) {
        int sum = 17 ;
        int[] coins = {2,5};
        List<Integer> newList = new ArrayList<>();
        newList.add(23);
        newList.add(90);
        newList.add(90);
        newList.add(39);
        newList.add(14);
        newList.add(14);


        System.out.println(newList);

        newList.toString();

        Set<Integer> setFromList = new HashSet<>(newList);

        System.out.println(setFromList.toString());

        //int result = coinsNeededDP(coins, sum);
        //System.out.println(result);
    }

    public static int coinsNeededDP(int[] coins, int sum){
        int minPerSum[] = new int[sum+1];

        Arrays.fill(minPerSum, Integer.MAX_VALUE);

        minPerSum[0] = 0;
//        for (int i = 0; i < coins.length ; i++) {
//            minPerSum[coins[i]] = 1;
//        }

        for(int i =1 ; i <= sum ; i++ ){
            for (int j = 0; j < coins.length ; j++) {
                if(coins[j] <= i) {
                    int tempMin = minPerSum[i - coins[j]];
                    if(tempMin != Integer.MAX_VALUE && tempMin < minPerSum[i])
                    {
                        minPerSum[i] = tempMin+1;
                    }
                }
            }
        }
        return minPerSum[sum];
    }
    public static int coinsNeededMemoizationInternal(int[] coins, int sum) {
        int minCoinsNeeded = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if(sum >= coins[i]) {
                int tempMin = coinsNeeded(coins, sum- coins[i]);
                if(tempMin + 1 < minCoinsNeeded)
                    minCoinsNeeded = tempMin + 1;
            }
        }
        return minCoinsNeeded;
    }

    public static int coinsNeededMemoization(int[] coins, int sum){
        if(sum == 0 ) return 0;

        int[] computed = new int [sum+1];
        Arrays.fill(computed,Integer.MAX_VALUE);

        for (int i = 0; i <coins.length ; i++) {
            computed[coins[i]] = 1;
        }

        return 1;
    }

    public static int coinsNeeded(int[] coins, int sum){
        if(sum == 0 ) return 0;

        int minCoinsNeeded = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if(sum >= coins[i]) {
                int tempMin = coinsNeeded(coins, sum- coins[i]);
                if(tempMin + 1 < minCoinsNeeded)
                    minCoinsNeeded = tempMin + 1;
            }
        }
        return minCoinsNeeded;
    }
}
