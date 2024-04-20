package Leetcode;

// https://leetcode.com/contest/weekly-contest-393/problems/latest-time-you-can-obtain-after-replacing-characters/
public class LatestTimeAfterReplacingCharacters {
    public static void main(String[] args) {
        LatestTimeAfterReplacingCharacters lt = new LatestTimeAfterReplacingCharacters();
        String str =  "1?:?4";
        System.out.println(lt.findLatestTime(str));
        String str1 =  "09:59";
        System.out.println(lt.findLatestTime(str1));
        String str2 = "0?:5?";
        System.out.println(lt.findLatestTime(str2));
        String str3 = "1?:0?";
        System.out.println(lt.findLatestTime(str3));
        String str4 = "0?:3?";
        System.out.println(lt.findLatestTime(str4));
        String str5 = "?3:12";
        System.out.println(lt.findLatestTime(str5));
        String str6 = "??:1?";
        System.out.println(lt.findLatestTime(str6));
        String str7 = "0?:??";
        System.out.println(lt.findLatestTime(str7));

    }
    public String findLatestTime(String s) {
        int[] max = new int[]{1,1,0,5,9};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch==':' || Character.isDigit(ch))
                sb.append(ch);
            else if(ch == '?'){
                int maxDigit = max[i]; // Applicable for

                if(i==0){
                    if(Character.isDigit(s.charAt(i+1)) && s.charAt(i+1)-'0'>= 2) maxDigit = 0;
                }

                if(i==1){
                    if(Character.isDigit(s.charAt(i-1)) && s.charAt(i-1)=='0') maxDigit = 9;
                }
                sb.append(maxDigit);
            }
        }
        return sb.toString();
    }


}
