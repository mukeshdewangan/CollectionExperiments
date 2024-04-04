package Leetcode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-anagrams/

public class GroupAnagrams {
    public static void main(String[] args) {
        //Map<Map<Character, Integer>, List<String>> stringAnagrams = new HashMap<>();
        List<String> input = List.of("cat", "tac", "bat", "ate", "tea", "eat", "bum");

        LocalDate date = LocalDate.parse("2022-01-02").minusYears(1);
        System.out.println(date);
        List<List<String>> result = groupAnagrams(input);
        for (List<String> strList: result) {
            System.out.println(strList);
        }
        int x = -121;
        String str = String.valueOf(x);
    }


    static List<List<String>> groupAnagrams(List<String> input){
        Map<Map<Character, Integer>, List<String>> stringAnagrams = new HashMap<>();
        for (String s: input ) {
            Map<Character,Integer> counts = getCharacterCounts(s);
            if(stringAnagrams.containsKey(counts)){
                //System.out.println(s + "contains ");
                List<String> strings = stringAnagrams.get(counts);
                strings.add(s);
                stringAnagrams.put(counts, strings);
            }
            else {
                //System.out.println( s + " do not contains ");
                List<String> strings = new ArrayList<>();
                strings.add(s);
                stringAnagrams.put(counts, strings);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Map<Character, Integer>, List<String>> entry: stringAnagrams.entrySet()) {
            List<String>  lists = entry.getValue();
            //for (String s : lists) System.out.println(s);
            result.add(lists);
        }
        return result;
    }

    static Map<Character, Integer> getCharacterCounts(String str){
        Map<Character,Integer> chars = new HashMap<>();
        for (char ch : str.toCharArray()) {
            chars.put(ch, chars.getOrDefault(ch, 0) +1);
        }
        return  chars;
    }

}
