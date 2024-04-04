package Leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAdjacentChars {
    public static void main(String[] args) {
        RemoveAdjacentChars r = new RemoveAdjacentChars();
        System.out.println(r.removeDuplicates("abbaca"));
        System.out.println(r.removeDuplicates("azxxzy"));
        System.out.println(r.removeDuplicates("ababaccabbaca"));
        System.out.println(r.removeDuplicates("aaaaa"));
        System.out.println(r.removeDuplicates("aaaaaaaa"));
    }
    // Two Pointers approach
    public String removeDuplicates(String s) {
        //char[] chars = s.toCharArray();
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        if(s.length() <=1 ) return s;
        int first =0; int second = 1;

        while(second < chars.size() && first < chars.size()){
            if(chars.get(first) != chars.get(second)){
                first++; second++;
            }
            else if(chars.get(first) == chars.get(second)){
                chars.remove(first);
                chars.remove(first);
                first--;
                second--;
                if(first<0){
                    first = second;
                    second++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : chars) sb.append(ch);

        return sb.toString();
    }

    // Two stack approach
    public String removeDuplicates1(String s) {
        return "";
    }

}
