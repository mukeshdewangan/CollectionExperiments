package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class InterleavingString {

    private static boolean areInterleavingInternal(String s1, String s2, String s3){
        if(s1.isEmpty()){
            return s2.equals(s3);
        }
        if(s2.isEmpty()){
            return s1.equals(s3);
        }
        boolean flag = false;
        if(s1.charAt(0) == s3.charAt(0))
        {
             flag = areInterleavingInternal(s1.substring(1), s2, s3.substring(1));
        }
        if(flag) return true;
        if(s2.charAt(0) == s3.charAt(0)){
            flag = areInterleavingInternal(s1, s2.substring(1), s3.substring(1));
        }

        return flag;
    }
    private static boolean areInterleavingString(String s1, String s2, String s3){
        if(s3.length() != s2.length() + s1.length()) return false;
        boolean finalRes =  areInterleavingInternal(s1, s2, s3);
        return finalRes;
    }

    public static void main(String[] args) {
        String s1 = "adbcc";
        String s2 = "dbbca";
        String s3 = "addbbcbcac";

        System.out.println(areInterleavingString(s1, s2, s3));
    }
}
