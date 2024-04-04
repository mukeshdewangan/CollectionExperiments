package Leetcode;

// https://leetcode.com/contest/weekly-contest-389/problems/existence-of-a-substring-in-a-string-and-its-reverse/
public class ExistenceOfSubStringInStringAndItsReverse {
    public static void main(String[] args) {
        ExistenceOfSubStringInStringAndItsReverse s= new ExistenceOfSubStringInStringAndItsReverse();
        String input = "abcbl";
        System.out.println( s.isSubstringPresent(input));
    }
    public boolean isSubstringPresent(String s) {
        if(s.length() < 2) return false;
        //char[] input= s.toCharArray();
        boolean result = false;
        StringBuilder reverse = new StringBuilder();
        // append a string into StringBuilder input1
        reverse.append(s);
        // reverse StringBuilder input1
        reverse.reverse();
        String rev = reverse.toString();
        for(int i=0;i<=s.length()-2;i++){
            //if(input[i]==input[i-1]) return true;
            String sub = s.substring(i,i+2);
            if(rev.contains(sub)) return true;
        }
        return false;
    }
}
