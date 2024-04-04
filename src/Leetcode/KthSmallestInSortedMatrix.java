package Leetcode;

import java.util.Objects;
import java.util.PriorityQueue;

//

public class KthSmallestInSortedMatrix {
    public static void main(String[] args) {
        KthSmallestInSortedMatrix kObj = new KthSmallestInSortedMatrix();
        int[][] matrix = new int[][]{{1,5,9},{10,11,13},{12,13,15}};
        int k =3;
        int result =  kObj.kthSmallest(matrix, k);
        System.out.println(k + "th element is " + result );

        matrix = new int[][]{{-5}};
        k = 1;
        result =  kObj.kthSmallest(matrix, k);
        System.out.println(k + "th element is " + result );

    }
    public int kthSmallest(int[][] matrix, int k) {
        Heap heap = new Heap(k);
        for(int i=0;i< matrix.length ;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                  heap.addToPQ(matrix[i][j]);
            }
        }
        if(heap.getLength() == k) {
            while (heap.getLength() > 0){

                //System.out.println(heap.getNext());
                //heap.remove();
                return heap.getNext();
            }
        }
        else{
            System.out.println("There less than k elements");
            return Integer.MIN_VALUE;
        }
        return 0;
    }
    static class Heap{
        final int size;
        PriorityQueue< Integer> pq = new PriorityQueue<>((a,b)->{ if(Objects.equals(a, b)) return 0; if(a>b) return -1; else return 1; });
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
