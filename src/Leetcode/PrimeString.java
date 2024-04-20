package Leetcode;

// Prime String
// Given a string of length n consisting of digits [0-9],
// count the number of ways the given string can be split into prime numbers.
// The digits must remain in the order given and the entire string must be used.
// Each number must be in the range 2 to 106 inclusive, and may not contain leading zeros.
// Since the answer can be large, return the answer modulo (10^9 + 7).
// Note: The initial string does not contain leading zeros.

// Example 1- s = "11375"
//
//This string can be split into primes 3 different ways: [11, 37, 5], [11,3, 7, 5], [113, 7, 5].

import java.util.ArrayList;
import java.util.List;

// Example 2 - s = "3175"
//
// The 3 ways to split this string into prime numbers are [31, 7, 5], [3, 17, 5], [317, 5]. 3 modulo (109 + 7) = 3
public class PrimeString {
    public static void main(String[] args) {
        String str = "30175"; // 301, 7,5
        System.out.println("str "+ str +" "+ countPrimeStrings(str) + "\n");
        str = "175";
        System.out.println("str "+ str +" "+ countPrimeStrings(str) + "\n");
        str= "11375";
        System.out.println("str "+ str +" "+ countPrimeStrings(str) + "\n");
        str= "24";
        System.out.println("str "+ str +" "+ countPrimeStrings(str) + "\n");
        str= "135029";
        System.out.println("str "+ str +" "+ countPrimeStrings(str) + "\n");
    }
    public static int countPrimeStrings(String s) {
        System.out.println("start " + s);
        List<Long> splits = new ArrayList<>();
        long answer = countWaysToPartitionAsPrimeArrays(s,0, splits);
        int subAnswer = (int) (answer % 1000000007);
        return subAnswer;
    }

    private static long countWaysToPartitionAsPrimeArrays(String s, int start, List<Long> splits){
        if(start == s.length()) return 1;
        long answer =0L;
        for (int i = start+1; i <= s.length() ; i++) {
            String firstPrefix = s.substring(start,i);
            //
            if(isPrime(firstPrefix)){
                long num = Long.parseLong(firstPrefix);
                splits.add(num);
                System.out.println("Prime " + num);
                long subAns = countWaysToPartitionAsPrimeArrays(s, i, splits);
                answer += subAns;
                splits.remove(splits.size()-1);
            }
        }
        return answer;
    }

    private static boolean isPrime(String numStr){
        if(numStr.startsWith("0")) return false;
        int num = Integer.parseInt(numStr);
        if( num == 0) return false;
        if( num ==1) return false;
        if(num <=3 ) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
}
