package Leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// https://leetcode.com/problems/reveal-cards-in-increasing-order/description/
public class RevealCardInIncreasingOrder {
    public static void main(String[] args) {
        int[] deck = {17,13,11,2,3,5,7};
        RevealCardInIncreasingOrder r = new RevealCardInIncreasingOrder();
        int[] res = r.deckRevealedIncreasing1(deck);
        for (int i  : res) {
            System.out.println( i);
        }
    }
    // Using LinkedList
    public int[] deckRevealedIncreasing1(int[] deck) {
        Arrays.sort(deck);
        //List<Integer> deckOfCards = List.of(Arrays.asList();
        List<Integer> linkedList = new ArrayList<>();

        linkedList.add(deck[deck.length-1]);
        for(int i = deck.length-2; i >= 0; i--){
            // Remove top and push at last
            int polled = linkedList.remove(linkedList.size()-1);
            linkedList.add(0,polled);

            // Add the current largest element from sorted deck list to front
            int nextLargest = deck[i];
            linkedList.add(0, nextLargest);
        }
        return listToIntArray(linkedList);
    }

    int[] listToIntArray(List<Integer> list){
        int[] res = new int[list.size()];
        for(int i=0; i< list.size();i++) {
            res[i]=list.get(i);
        }
        return res;
    }
}
