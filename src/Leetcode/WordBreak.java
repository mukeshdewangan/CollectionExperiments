package Leetcode;

import java.util.List;
import java.util.ArrayList;
//https://leetcode.com/problems/word-break/description/
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        List<String> wordDict = new ArrayList<>(); wordDict.add("leet"); wordDict.add("code");
        String s = "leetcodeleet";
        System.out.println(s + " " +   wb.wordBreak(s,wordDict));

        wordDict = new ArrayList<>(); wordDict.add("apple"); wordDict.add("pen");
        s = "applepenapple";
        System.out.println(s + " " +   wb.wordBreak(s,wordDict));

        wordDict = new ArrayList<>(); wordDict.add("cats"); wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        s = "catsandog";

        System.out.println(  s + " " +  wb.wordBreak(s,wordDict));

        wordDict = new ArrayList<>(); wordDict.add("b"); wordDict.add("ba"); wordDict.add("bbbb");
        s = "a";
        System.out.println(  s + " " +  wb.wordBreak(s,wordDict));

        s = "cars";
        wordDict = new ArrayList<>(); wordDict.add("car"); wordDict.add("ca"); wordDict.add("rs");
        System.out.println(  s + " " +  wb.wordBreak(s,wordDict));

    }


    public boolean wordBreak(String s, List<String> wordDict) {
        boolean result = false;

        int[][] memo = new int[s.length()][2];

        for (int i = 0; i < memo.length; i++) {
            memo[i][0] = -1;
            memo[i][1] = 0;
        }

        result = canMakeWord(s, s.length(), wordDict, 0, memo);
        //System.out.println("result " + result);
        return result;
    }
    private boolean canMakeWord(String str, int originalLength, List<String> wordDict, int index, int[][] memo){
        boolean result = false;
        if(index == originalLength) return true;

        if(memo[index][0] != -1)
            return memo[index][1] == 1;

        for (String word : wordDict) {
            if(str.startsWith(word)){
                String remainStr = str.substring(word.length());
                int newIndex = index+ word.length();
                result = result | canMakeWord(remainStr, originalLength , wordDict, newIndex, memo);
            }
        }
        memo[index][0] = 1;
        memo[index][1] = result ? 1: 0;
        return result;
    }

}
