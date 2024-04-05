package Leetcode;

public class WonderfulString {

    public static void main(String[] args) {
        WonderfulString ws = new WonderfulString();
        System.out.println(ws.wonderfulSubstrings("aba"));
        System.out.println(ws.wonderfulSubstrings("abab"));
    }
    public long wonderfulSubstrings(String word) {
        int[] occur ;//= new int[10];
        int len = word.length();
        int oddCount = 0;
        long result = 0L;
        char[] chars = word.toCharArray();

        for(int i = 0; i < len;i++){
            occur = new int[10];
            oddCount = 0;
            for(int j= i ; j< len ;j++){
                char ch = chars[j];
                int index = (ch-'a');
                occur[index]++;
                if(occur[index]%2 == 0)
                    oddCount--;
                else
                    oddCount++;
                if(oddCount <=1)
                    result++;
            }
        }
        return result;
    }
}
