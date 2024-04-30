package Leetcode;

import java.util.*;

// https://leetcode.com/problems/count-the-number-of-special-characters-i/description/
// https://leetcode.com/problems/count-the-number-of-special-characters-ii/description/
public class SpecialWordContest394 {
    public static void main(String[] args) {
        SpecialWordContest394 s = new SpecialWordContest394();
        System.out.println(s.numberOfSpecialChars2( "aaAbcBC"));
        System.out.println(s.numberOfSpecialChars2( "abc"));
        System.out.println(s.numberOfSpecialChars2( "AbBCab"));
        System.out.println(s.numberOfSpecialChars2( "aaaaaABbbbBC"));
        System.out.println(s.numberOfSpecialChars2( "AbcbDBdD"));

    }

    public int numberOfSpecialChars(String word) {
        Map<Integer, Integer> a = new HashMap<>();
        List<Integer> list = new ArrayList<>(a.keySet());

        int[] lower = new int[26];
        int[] upper = new int[26];
        for (char ch : word.toCharArray() ) {
            if(Character.isLowerCase(ch)){
                lower[ch-'a']++;
            }else {
                upper[ch-'A']++;
            }
        }
        int result =0;
        for (int i = 0; i < 26; i++) {
            if(lower[i] > 0 &&  upper[i] > 0) {
                result++;
            }
        }
        return result;
    }


    public int numberOfSpecialChars2(String word) {
        int[] lower = new int[26];
        int[] maxLowerCase = new int[26];
        Set<Character> alreadySet = new HashSet<>();
        for (char ch : word.toCharArray() ) {
            if(Character.isLowerCase(ch)){
                lower[ch-'a']++;
            }else {
                if( !alreadySet.contains(ch) && maxLowerCase[ch-'A'] == 0) {
                    maxLowerCase[ch - 'A'] = lower[ch - 'A'];
                    alreadySet.add(ch);
                }
            }
        }
        int result =0;
        for (int i = 0; i < 26; i++) {
            if(lower[i] != 0 && lower[i] == maxLowerCase[i]) {
                result++;
            }
        }
        return result;
    }
}
