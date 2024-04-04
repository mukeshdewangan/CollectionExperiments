package Leetcode;

//https://leetcode.com/problems/super-palindromes/
public class SuperPalindromes {


    private static boolean isPalindrome(long n){
        String input = (new Long(n)).toString();
        int len = input.length();
        for(int i =0 ; i< len/2; i++ ){
            if(input.charAt(i) != input.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        String first_s = "9944094036";
        String last_s = "431375128285413";

        long first = (long)Math.sqrt(Double.parseDouble(first_s));
        long last = (long)Math.sqrt(Double.parseDouble(last_s));
        System.out.println("first square root " + first);
        System.out.println("last square root " + last);
        for(long num = first; num <= last; num++){
            if(isPalindrome(num) && isPalindrome(num*num)){
                System.out.println( num );
            }
        }
        System.out.println("DONE");
    }
}

