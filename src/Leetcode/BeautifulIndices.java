package Leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/contest/weekly-contest-380/problems/find-beautiful-indices-in-the-given-array-i/
public class BeautifulIndices {
    public static void main(String[] args) {
//       String s = "isawsquirrelnearmysquirrelhouseohmy";
//       String a = "my";
//       String b = "squirrel";
//       int k = 15;

        String s = "frtzggff";
        String a = "g";
       String b = "f";
       int k = 1;
       BeautifulIndices bI = new BeautifulIndices();
        //System.out.println( bI.allIndicesOfOccurrences(s, a));
        //System.out.println( bI.allIndicesOfOccurrences(s, b));
        System.out.println( bI.beautifulIndices(s,a,b,k));

    }
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> first = allIndicesOfOccurrences(s, a);
        List<Integer> second = allIndicesOfOccurrences(s, b);
        List<Integer> res = new ArrayList<>();
        int firstPtr = 0;
        int secondPtr = 0;
        while(firstPtr<first.size() && secondPtr < second.size()){
            if(Math.abs(first.get(firstPtr) - second.get(secondPtr)) <=k){
                res.add(first.get(firstPtr));
                firstPtr++;
            }
            else if( first.get(firstPtr) < second.get(secondPtr)){// && Math.abs(first.get(firstPtr) - second.get(secondPtr)) > k){
                firstPtr++;
            }
            else if( first.get(firstPtr) > second.get(secondPtr)) {// && Math.abs(first.get(firstPtr) - second.get(secondPtr)) > k){
                secondPtr++;
            }
        }
        return  res;
    }

    List<Integer> allIndicesOfOccurrences(String s, String t){
        List<Integer> res = new ArrayList<>();
        int lastIndex =0;
        while(lastIndex !=-1){
            int aIndex = s.indexOf(t,lastIndex);
            if(aIndex == -1) {
                lastIndex= -1;
            }
            else {
                res.add(aIndex);
                lastIndex = aIndex + t.length();
            }
        }
        return res;
    }
}
