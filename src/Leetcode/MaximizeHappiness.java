package Leetcode;

import jnr.ffi.annotations.In;

import java.util.Map;
import java.util.PriorityQueue;

public class MaximizeHappiness {
    public static void main(String[] args) {
        MaximizeHappiness h = new MaximizeHappiness();
        int[] happiness = {2,4,1,6,3};
        int k = 3;
        System.out.println(h.maximumHappinessSum(happiness,k));

        happiness = new int[]{1, 2, 3};
        k = 2;
        System.out.println(h.maximumHappinessSum(happiness,k));

        happiness = new int[]{1,1,1,1};
        k = 2;
        System.out.println(h.maximumHappinessSum(happiness,k));

        happiness = new int[]{2,3,4,5};
        k = 1;
        System.out.println(h.maximumHappinessSum(happiness,k));

        happiness = new int[]{12,1,42,13,63,562};
        k = 3;
        System.out.println(h.maximumHappinessSum(happiness,k));

        happiness = new int[]{12,1,42};
        k = 3;
        System.out.println(h.maximumHappinessSum(happiness,k));
    }
    public long maximumHappinessSum(int[] happiness, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int j : happiness) {
            pq.add(j);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] arr = new int[k];
        int i=k-1;
        while (!pq.isEmpty()){
            int val = pq.poll();
            arr[i--] = val;
        }

        long sum = 0;
        int decrement = 0;
        for (int value : arr) {
            //System.out.println(value);
            int value1 = value - decrement;
            decrement++;
            if(value1 <=0 ) break;
            sum += value1;
        }

        return sum;
    }

    public long maximumHappinessSum1(int[] happiness, int k) {
        Heap heap = new Heap(k);

        for (int i = 0; i < happiness.length; i++) {
            heap.addToPQ(happiness[i]);
        }
        int sum = 0;
        int increment = 0;
        while(heap.getLength() > 0){
            int value = heap.getNext();
            value = value - increment;
            if(value<=0) break;
            sum += value;
            increment++;
            heap.remove();
        }
        return sum;
    }
    static class Heap{
        final int size;
        PriorityQueue< Integer> pq = new PriorityQueue<>();
        Heap(int k){
            this.size =k;
        }

        private void addToPQ(Integer entry){
            pq.add(entry);
            if(pq.size() > size){
                pq.poll();
            }
        }

        private int getLength(){
            return pq.size();
        }

        private Integer getNext(){
            //assert pq.peek() != null;
            return pq.peek();
        }

        public void remove() {
            pq.poll();
        }
    }
}
