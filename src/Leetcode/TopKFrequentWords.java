package Leetcode;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = {"op","i","love", "love","leetcode","i","love","coding", "op","op",};
        int k = 2;
        List<String> res = topKFrequent(words, k);
        System.out.println(res);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> counts = new HashMap<>();
        for (String s : words) {
            counts.put(s, counts.getOrDefault(s, 0)+1);
        }

        Heap heap = new Heap(k);
        for(Map.Entry<String, Integer> entry : counts.entrySet()){
            heap.addToPQ(entry);
        }

        while(heap.getLength() > 0){
            res.add(heap.getNext().getKey());
            heap.remove();
        }
        return res;
    }

    // Heap with fixed size
    static class Heap{
        final int size;
        //PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b)->{ return a.getValue() - b.getValue();});
        Heap(int k){
            this.size =k;
        }

        private void addToPQ(Map.Entry<String, Integer> entry){
            pq.add(entry);
            if(pq.size() > size){
                pq.poll();
            }
        }

        private int getLength(){
            return pq.size();
        }

        private Map.Entry<String, Integer> getNext(){
            //assert pq.peek() != null;
            return pq.peek();
        }

        public void remove() {
            pq.poll();
        }
    }
}
