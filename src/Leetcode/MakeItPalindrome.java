package Leetcode;

//
//Given a string which we can delete at most k, return whether you can make a palindrome.

// For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
public class MakeItPalindrome {
    public static void main(String[] args) {
        String s = "waterrfetawx";
        MakeItPalindrome p = new MakeItPalindrome();
    }

    public boolean makeItPalindrome(String s){
        int[] count = new int[26];
        for (char ch: s.toCharArray()) {
            count[ch - 'a']++;
        }
        return true;
    }

}
