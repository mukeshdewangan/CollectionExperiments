package Leetcode;

import java.util.*;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> charOcc = new HashMap<>();

        for (int i = 0; i < tasks.length ; i++) {
            charOcc.put(tasks[i], charOcc.getOrDefault(tasks[i], 0)+1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a,b)-> b.getValue() - a.getValue());
        pq.addAll(charOcc.entrySet());

        Queue<Map.Entry<Character,Integer>> entryQueue = new LinkedList<>();
        //entryQueue.addAll(pq);

        while (!pq.isEmpty()){
            entryQueue.add(pq.poll());
        }

        charOcc = new HashMap<>();
        int maxLength = -1;
        while (!entryQueue.isEmpty()){
            maxLength++;
            //System.out.println(entryQueue.poll());
            Map.Entry<Character, Integer> charCountEntry = entryQueue.poll();
            Character character = charCountEntry.getKey();
            int remaining = charCountEntry.getValue();

            if(charOcc.containsKey(character)) {
                int lastPosition = charOcc.get(character);
                while (maxLength - lastPosition <= n) {
                    System.out.print("_");
                    maxLength++;
                }
            }

            System.out.print(character);
            charOcc.put(character,maxLength);
            remaining--;
            if(remaining!=0){
                charCountEntry.setValue(remaining);
                entryQueue.add(charCountEntry);
            }
        }
        System.out.print(maxLength+1);
        return maxLength+1;
    }

    public static void main(String[] args) {
        //leastInterval(new char[]{'A','D','B','A','A','B','A','A', 'C', 'D', 'A','B' }, 3);
        leastInterval(new char[]{'A','B','A','A','B','B' }, 2);
    }
}
