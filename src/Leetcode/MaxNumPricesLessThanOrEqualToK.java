package Leetcode;

//https://leetcode.com/contest/weekly-contest-380/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/
public class MaxNumPricesLessThanOrEqualToK {
    public static void main(String[] args) {
        MaxNumPricesLessThanOrEqualToK m = new MaxNumPricesLessThanOrEqualToK();
        System.out.println( m.generateNumberWithEveryXthBitSet(2));
    }

    long generateNumberWithEveryXthBitSet(int x){
        long num =1;
        for (int i =0;i<=63;i++ ){
            if((i+1)%x==0){
                num |= (1L << i);
            }
        }
        //Long integer = num;
        System.out.println(Long.toBinaryString(num));
        long setBitCount = countSetBits(num);
        System.out.println(" set bit count " + setBitCount);
        return num;
    }

    public static int countSetBits(long n)
    {
        int count =0;
        // base case
        while (n != 0){
            count++;
            n = n&(n-1);
        }
        return count;
    }

    // Constraints
    // 1 <= k <= 1015
    // 1 <= x <= 8
    public long findMaximumNumber(long k, int x) {
        return 0;
    }


}
