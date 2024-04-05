package Leetcode;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
// https://leetcode.com/problems/maximum-product-of-word-lengths/
public class MaximumProductsOfWordLengths {
    public static void main(String[] args) {
        //String[] words = {"abcw","baz","fo","bar","xtfabn","abcdef"};
        String[] words = {"cb", "aaa", "aaaa", "aaaaaa"};
        //BitSet[] list = new BitSet[words.length];

        boolean[][] exists = new boolean[words.length][26];
        // Calculate the bitset for all words
        int counter=0;
        for (String word : words) {
            //list[counter++] = (setBitsForString(word));
            exists[counter++] = setBits(word);
        }

        // Max Product of lengths
        //
        long maxProduct = 0;
        for ( int i=0;i< exists.length ;i++ ) {
            for (int j=i+1; j< exists.length ;j++){
                //list[i].xor(list[i]);
                boolean hasCommon = hasCommonBitSet(exists[i], exists[j]);
                if(!hasCommon){
                    long prod = words[i].length() * words[j].length();
                    if(prod > maxProduct)
                        maxProduct = prod;
                }
            }
        }
        //return maxProduct;
        System.out.println(" max product of word length  "+ maxProduct);

    }
    static boolean hasCommonBitSet(boolean[] first, boolean[] second){
        for (int i=0;i< first.length;i++){
            if(first[i] && second[i]) return true;
        }
        return false;
    }

    static boolean[] setBits(String str){
        boolean[] bitSet = new boolean[26];
        char[] chars = str.toCharArray();
        for (char ch : chars ) {
            bitSet[ch-'a'] = true;
        }
        return bitSet;
    }

    static BitSet setBitsForString(String str){
        BitSet bitSet = new BitSet(26);
        char[] chars = str.toCharArray();
        for (char ch : chars ) {
           int index=  ch-'a';
            bitSet.set(index);
        }
        return bitSet;
    }


}
