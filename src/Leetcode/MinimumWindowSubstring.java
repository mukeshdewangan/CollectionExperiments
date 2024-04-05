package Leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

//https://leetcode.com/problems/minimum-window-substring/
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        // Consider only lower case;
        String  str = "adobecodebanc"; String t = "abc";
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        System.out.println(m.minWindow(str, t));
    }

    public String minWindow(String s, String p) {
        String res = "";
        if(p.length() > s.length()) return res;
        Map<Character,Integer> strCharCount = new HashMap<>();
        Map<Character,Integer> patternCharCount = new HashMap<>();
        for(int i=0;i< p.length();i++){
            char ch = p.charAt(i);
            patternCharCount.put(ch, patternCharCount.getOrDefault(ch, 0)+1);
        }

        char[] str = s.toCharArray();

        int minStart =0;
        int minEnd = 0;
        int minSize = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        while(start< str.length && !patternCharCount.containsKey(str[start])){
            start++;
        }
        end = start;
        while(start< str.length && end < str.length){
            while(end < str.length){
                if(patternCharCount.containsKey(str[end])){
                    strCharCount.put(str[end], strCharCount.getOrDefault(str[end], 0)+1);
                }
                if(containsEveryCharacterOfPattern(patternCharCount, strCharCount))
                    break;
                end++;
            }
            if(end < str.length){
                int size = end-start+1;
                if(size < minSize){
                    minSize = size;
                    minStart = start;
                    minEnd = end;
                }
            }
            strCharCount.put(str[start], strCharCount.getOrDefault(str[start], 0)-1);

            start=start+1;
            while(start < str.length && !patternCharCount.containsKey(str[start])){
                start++;
            }
            end++;
        }
        System.out.println("Max Size " + (minEnd-minStart+1));
        String subString = s.substring(minStart, minEnd+1);
        return subString;
    }
    boolean containsEveryCharacterOfPattern(Map<Character, Integer> pattern, Map<Character, Integer> strCharCount){
        for (Map.Entry<Character, Integer> entry: pattern.entrySet()) {
            Character ch = entry.getKey();
            if(!strCharCount.containsKey(ch)) return false;
            if(entry.getValue() > strCharCount.get(ch)) return false;
        }
        return true;
    }
}
