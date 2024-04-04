package Leetcode;


import java.util.List;
import java.util.ArrayList;


//https://www.hackerrank.com/x/library/hackerrank/all/questions/1369748/try
public class PasswordReset {
    public static void main(String[] args) {
        // expected
         String str = new String("aaaaaaaabbbbbbbccccccccccddddddddeeeeeeeeefffffffffffgggggghhhhhhhhiiiiiiiijjjjjjjjjjkkkkkkkkkkkkllllllllllllmmmmmmmmnnnnnnnnnooooooooppppppqqqqqqqrrrrrrrrsssssssssttttttuuuuuuuuuvvvvvvvwwwwxxxxxyyyyyyyyyzzzzzzzzozzzzzzzzyyyyyyyyyxxxxxwwwwvvvvvvvuuuuuuuuuttttttsssssssssrrrrrrrrqqqqqqqppppppoooooooonnnnnnnnnmmmmmmmmllllllllllllkkkkkkkkkkkkjjjjjjjjjjiiiiiiiihhhhhhhhggggggfffffffffffeeeeeeeeeddddddddccccccccccbbbbbbbaaaaaaaa");
        // actual output
        //String str = new String("aaaaaaaabbbbbbbccccccccccddddddddeeeeeeeeefffffffffffgggggghhhhhhhhiiiiiiiijjjjjjjjjjkkkkkkkkkkkkllllllllllllmmmmmmmmnnnnnnnnnoooooooooppppppqqqqqqqrrrrrrrrsssssssssttttttuuuuuuuuuvvvvvvvwwwwxxxxxyyyyyyyyyzzzzzzzozzzzzzzyyyyyyyyyxxxxxwwwwvvvvvvvuuuuuuuuuttttttsssssssssrrrrrrrrqqqqqqqppppppooooooooonnnnnnnnnmmmmmmmmllllllllllllkkkkkkkkkkkkjjjjjjjjjjiiiiiiiihhhhhhhhggggggfffffffffffeeeeeeeeeddddddddccccccccccbbbbbbbaaaaaaaa");
        int[] charCount = new int[26];
        for (char ch: str.toCharArray()) {
            charCount[ch-'a']++;
        }

        for (int i =0; i < 26;i++) {
            if(charCount[i] > 0) {
                System.out.println((char) ('a' + i) + ":" + charCount[i]);
            }
        }

        String input = new String("mwvzllkujidhukzwzcltgqngguftuahalwvjwqncksizgzajkhyjujlkseszafzjmdtsbyldhylcgkyngvmhneqyjdugofklitxaoykfoqkzsznjyarkuprerivhubpehxmoydakklbdnfhfxamotubelzvbozjaraefmlotftnqrjolvuamahndekfdsqcfvmqbocbomjxrqsfskevfxphcqoqkbbomcyurwlrnhrhctntzlylvwulbdkcdppgykichjtpukfnlxfcevkjezqsmeycanjlbessrfazdprcomdpjimsfbuslksyveergcgmonctcsvypolplcgsqyfkilrixodiwqcyreiwkrpiuiasfkjexpftznqiblsrjuyfskndapwjefucdqciuehvfndghrxxnmvzljxioy");
        System.out.println(findResultantString(input));
    }

    public static String findResultantString(String s) {
        // Write your code here
        char[] inputStr = s.toCharArray();
        int[] countChars = new int[26];
        for(int i = 0; i < inputStr.length; i++){
            int pos = inputStr[i] - 'a';
            countChars[pos]++;
        }
        List<Character> charList = new ArrayList<>();
        for(int i=0;i<26;i++) {
            if(countChars[i]%2 == 1){
                charList.add((char)('a'+i));
            }
        }

        // for(Character ch: charList) {
        //     System.out.println(ch);
        // }

        int mid = charList.size()/2;
        for(int i = 0; i < mid; i++){
            char former = charList.get(i);
            countChars[former-'a']++;
            char latter = charList.get(charList.size()-1-i);
            countChars[latter-'a']--;
        }

        // for(int i=0;i<26;i++) {
        //     if(countChars[i]>0)
        //     System.out.println((char)('a'+i) + ":" + countChars[i]);
        // }
        int len = s.length();
        char[] resultant = new char[len];
        // for odd length
        for(int i=0;i<26;i++) {
            if (countChars[i]%2 == 1){
                resultant[len/2] = (char)(i+'a');
                countChars[i]--;
            }
        }
        int counter = 0;
        for(int i=0;i<26;i++) {

            while(counter < len/2 && countChars[i]>=2){
                resultant[counter] = (char)('a'+i);
                resultant[len-1-counter] = (char)('a'+i);
                counter++;
                countChars[i]--;countChars[i]--;
            }

        }

        return new String(resultant).replace(" ", "");
    }
}
