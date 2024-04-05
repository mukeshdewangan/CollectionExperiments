package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {

    static boolean isomorphicString(String s, String t){
        return isomorphicStringInternal(s,t) && isomorphicStringInternal(t,s);
    }
    static boolean isomorphicStringInternal(String s , String t){
        Map<Character, Character> charMap = new HashMap<>();

        for(int i =0; i< s.length();i++){
            Character ch = s.charAt(i);
            Character target= t.charAt(i);
            if(charMap.containsKey(ch)){
                if (charMap.get(ch) != target)
                    return false;
            }
            else{
                charMap.put(ch,target);
            }
        }
        return true;
    }
    static boolean isomorphicModified(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        Map<Character, Character> s1Map = new HashMap<>();
        Map<Character, Character> s2Map = new HashMap<>();

        for(int i =0; i< s1.length();i++){
            Character first = s1.charAt(i);
            Character second = s2.charAt(i);
            if(!s1Map.containsKey(first) && !s2Map.containsKey(second)){
                s1Map.put(first,second);
                s2Map.put(second,first);
            }
            else if (s1Map.containsKey(first) && s2Map.containsKey(second)){
                if (s1Map.get(first) != second || s2Map.get(second) != first) return false;
            }
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(isomorphicModified("paper", "title"));
        System.out.println(isomorphicModified("add", "egg"));
        System.out.println(isomorphicModified("foo", "egg"));
        System.out.println(isomorphicModified("foo", "bar"));
        System.out.println(isomorphicModified("bada", "baba"));
    }
}
