package Leetcode;

// https://leetcode.com/contest/weekly-contest-389/problems/count-substrings-starting-and-ending-with-given-character/
public class CountSubstringsStartingandEnding {


    public static void main(String[] args) {
        System.out.println( countSubstrings("abada", 'a'));
        System.out.println( countSubstrings("baabadak", 'a'));
        System.out.println( countSubstrings("zzz", 'z'));
    }
    public static long countSubstrings(String s, char c) {
        int countC = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==c) countC++;
        }
        long result = ((long)(countC)*(countC+1))/2;
        return result;
    }
}
