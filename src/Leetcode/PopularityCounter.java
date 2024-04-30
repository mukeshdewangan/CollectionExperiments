package Leetcode;

import java.util.*;

/***
 * User - add/decrease
 * Popularity - add/decrease
 *
 * In case of tie -> older / insertion
 */

public class PopularityCounter {
    private final Map<String, Long> popularityCount = new LinkedHashMap<>();
    private final PriorityQueue<String> maxHeap = new PriorityQueue<>( (userId1, userId2)-> {
        long l = popularityCount.get(userId2) - popularityCount.get(userId1);
        if(l<0) return -1;
        else if(l>0) return 1; else return 0;
    } );

    public static void main(String[] args) {
        PopularityCounter pc = new PopularityCounter();
        String  first = "first";
        String second = "second";
        String third = "third";
        pc.addUser(first);
        pc.addUser(second);
        pc.addUser(third);

        pc.increment(second);pc.increment(second);pc.increment(second);pc.increment(second);pc.increment(second);

        pc.increment(first);pc.increment(first);pc.increment(first);pc.increment(first);

        pc.removeUser(second);
        pc.increment(third);pc.increment(third);pc.increment(third);

        System.out.println(pc.mostPopularUser());
    }

    public void increment(String userId) {
        popularityCount.put(userId, popularityCount.getOrDefault(userId,0L)+1);
        maxHeap.offer(userId);
    }

    public void decrement(String userId){
        if(popularityCount.containsKey(userId)) {
            long count = popularityCount.get(userId);
            if(count>0) {
                popularityCount.put(userId, popularityCount.get(userId) - 1);
            }
            maxHeap.remove(userId);
        }
    }

    public String mostPopularUser(){
        List<Long> values = new ArrayList<>(popularityCount.values());

        Collections.sort(values);// O(nlogn)
        String strTop ="";
        Long top = values.get(values.size()-1);
        for (Map.Entry<String, Long> entry: popularityCount.entrySet()) {
            if(Objects.equals(entry.getValue(), top)) {
                strTop = entry.getKey();
            }
        }
        System.out.println("Brute-force " + strTop);
        strTop= maxHeap.peek();
        System.out.println("With Max-Heap " + strTop);
        return strTop;
    }


    public void addUser(String userId){
        if(!popularityCount.containsKey(userId))
            return;
        popularityCount.put(userId,0L);
    }

    public void removeUser(String userId){
        if (!popularityCount.containsKey(userId)) return;
        popularityCount.put(userId, popularityCount.get(userId) - 1);
        if (popularityCount.get(userId) <= 0) {
            popularityCount.remove(userId);
            maxHeap.remove(userId);
        }
    }
}




