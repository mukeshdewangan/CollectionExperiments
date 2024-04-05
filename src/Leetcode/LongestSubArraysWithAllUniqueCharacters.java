package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArraysWithAllUniqueCharacters {

    public static int lengthOfLongestSubstring(String s) {
        char[] input = s.toCharArray();
        Map<Character, Integer> charPos = new HashMap<>();

        int start=0;
        int max = 0;
        // iterate from
        for(int current=0 ; current < s.length(); current++ ){

            char ch = input[current];
            // increment the start to map.get(ch) + 1 ony when start is ahead
            if(charPos.containsKey(ch) && start <= charPos.get(ch)) {
                start = charPos.get(ch)+1;
            }
            else {
                max = Math.max(max, current - start+1);
            }
            charPos.put(ch, current);
        }
        return max;
    }
    public static void main(String[] args) {

        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));

        System.out.println(lengthOfLongestSubstring(""));

        System.out.println(lengthOfLongestSubstring("bbbbb"));

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
