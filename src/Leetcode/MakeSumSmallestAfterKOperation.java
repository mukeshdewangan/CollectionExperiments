package Leetcode;

import org.objectweb.asm.tree.InnerClassNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
public class MakeSumSmallestAfterKOperation {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(10);
        input.add(20);
        input.add(7);
        int k = 4;
        System.out.println(minSum(input, k));

        input = new ArrayList<>();
        input.add(2);
        k = 1;
        System.out.println(minSum(input, k));
    }
    public static int minSum(List<Integer> nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());

        for (Integer num : nums) {
            pq.add(num);
        }

        int operationCount = 1;
        while(operationCount<=k){
            Integer top = pq.poll();
            int divideBy2 = (int)Math.ceil((float)top/2);
            pq.add(divideBy2);
            operationCount++;
        }
        int sum = 0;
        while(!pq.isEmpty()) {
            sum+=pq.poll();
        }
        return  sum;
    }

}
