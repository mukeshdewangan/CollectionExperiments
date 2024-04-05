package Leetcode;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumDeletionsToMakeStringKSpecial {
    public static void main(String[] args) {
        String word = "dabdcbdcdcd";
        int k = 2;
        System.out.println(minimumDeletions(word,k));
        word = "aaabaaa"; k=2;
        System.out.println(minimumDeletions(word,k));
        word = "happppppppppppy"; k=1;
        System.out.println(minimumDeletions(word,k));
    }
    public static int minimumDeletions(String word, int k) {
        char[] chars = word.toCharArray();
        int[] charFreq = new int[26];
        for (int i = 0; i < chars.length ; i++) {
            charFreq[chars[i] - 'a']++;
        }

        List<Integer> sortedFreq = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (charFreq[i] !=0) {
                sortedFreq.add(charFreq[i]);
            }
        }
        Collections.sort(sortedFreq);
        int index = 0;
        //int limit = sortedFreq.get(sortedFreq.size()-1);


        int minimumDeletion = Integer.MAX_VALUE;
        while(index < sortedFreq.size()){
            int start = sortedFreq.get(index);
            int end = start+k;
            int localDeletion = 0;
            for (Integer freq : sortedFreq) {
                if(freq > end) {
                    localDeletion += (freq-end);
                }
                if(freq < start) {
                    localDeletion += freq;
                }
            }
            if(localDeletion < minimumDeletion)
                minimumDeletion = localDeletion;
            index++;
        }
        return minimumDeletion;
    }
}
