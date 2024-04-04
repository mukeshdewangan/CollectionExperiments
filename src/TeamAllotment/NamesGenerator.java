package TeamAllotment;

import java.util.*;

public class NamesGenerator {


    public static int getPalindromeOfLength3(String s){
        int[][] occurance = new int[2][26];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26 ; j++) {
                occurance[i][j] = -1;
            }
        }
        // Record the first occurrence and last occurrence of all 26 characters
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length ; i++) {
            int pos = input[i] - 'a';

            if(occurance[0][pos] == -1) // first occurrence recorded
                occurance[0][pos] = i;
            else
                occurance[1][pos] = i;
        }

        int result = 0;
        // For all 26 characters, we need to find the intermediate unique element
        for (int i = 0; i < 26 ; i++) {
            // We can make a 3 length palindrome if it occurs atleast twice i.e first and last occurrence should not be -1
            if(occurance[0][i] != -1 && occurance[1][i] != -1) {
                Set<Character> set = new HashSet<>();
                int start = occurance[0][i] + 1;
                int end = occurance[1][i] - 1 ;
                while(start <= end){
                    set.add(input[start]);
                    start++;
                }
                result += set.size();
            }
        }
        return result;
    }
    // DOES NOT WORK - has corner case
    public static int getPalindromicSequenceCount3(String s){

        char[] input = s.toCharArray();
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            charCount.put(input[i], charCount.getOrDefault(input[i],0) + 1);
        }

        // Keep track of first 2 letters to determine uniqueness
        int result = 0;
        Set<String> unique = new HashSet<>();
        for(int i = 0; i < input.length-2; i++) {
            char ch = input[i];
            int count = charCount.get(ch);
            count--;
            if (count == 0) {
                charCount.remove(ch);
                continue;
            } else charCount.put(ch, count);

            // Find the second part of palindrome from i+1
            for (int j = i + 1; j < input.length - 1; j++) {
                char mid = input[j];
                String str = new String(new char[]{ch, mid});
                if (unique.contains(str))
                    continue;
                unique.add(str);
                if (mid == ch && charCount.getOrDefault(ch, 0) >= 2) {
                    // count should be more than >=2
                    result++;
                } else if (mid != ch && charCount.getOrDefault(ch, 0) >= 1)
                    result++;
            }
        }
        return result;
    }

    public static void main(String[] args){

        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_CYAN = "\u001B[36m";
        int phoneNu = 23424;
        System.out.println( ANSI_GREEN + "My Name is Mukesh " + phoneNu + ANSI_RESET);
        int res = NamesGenerator.getPalindromeOfLength3("bbcbaba");
        System.out.println(res);

        res = NamesGenerator.getPalindromeOfLength3("aabca");
        System.out.println(res);

        res = NamesGenerator.getPalindromeOfLength3("adc");
        System.out.println(res);

        res = NamesGenerator.getPalindromeOfLength3("ckafnafqo");
        System.out.println(res);
    }

}
