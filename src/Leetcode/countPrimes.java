package Leetcode;

// https://leetcode.com/problems/count-primes/
public class countPrimes {
    public static void main(String[] args) {
        int num = new countPrimes().countPrimesNumbers(8);
        System.out.println(num);
    }
    public int countPrimesNumbers(int n) {

        if(n <= 2) return 0;
        if(n == 3) return 1;
        if(n <= 5) return 2;

        int count = 2;

        int limit = n/6;
        for( int i=1; i<=limit;i++){
            //6n-1
            int intermediate = 6*i - 1;

            if(intermediate < n && isPrimeNumber(intermediate))
                count++;
            //6n+1
            intermediate += 2;
            if(intermediate < n && isPrimeNumber(intermediate))
                count++;
        }
        return count;
    }

    private boolean isPrimeNumber(int num){
        int sqrt = (int)Math.sqrt(num);
        for(int i=3; i<= sqrt; i++){
            if(num%i==0) return false;
        }
        return true;
    }
}