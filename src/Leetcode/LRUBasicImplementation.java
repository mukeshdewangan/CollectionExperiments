package Leetcode;

import javax.ws.rs.core.Link;
import java.util.*;

public class LRUBasicImplementation {
    final int size;

    Deque<Integer> deque = new LinkedList<>();
    Map<Integer, String> map = new HashMap<>();
    //List<>
    public LRUBasicImplementation(int size) {
        this.size = size;
    }

    public static void main(String[] args) {
        int size = 3;
        LRUBasicImplementation lru = new LRUBasicImplementation(size);

        lru.add(9, "nine");
        lru.add(5, "five");
        lru.add(3, "three");
        lru.printVal(5);
        lru.add(8, "eight");
        lru.printVal(4);
        lru.printVal(8);
        lru.printVal(5);
        lru.add(4, "four");
        lru.printVal(4);
    }

    public void printVal(int val){
        String res = this.get(val);
        if (res== null) System.out.println(" NULL ");
        else System.out.println(res);

    }

    public void add(Integer key, String value){
        if(map.containsKey(key)){
            deque.remove(key);

        }
        else {
            if (deque.size() >= size) {
                deque.removeLast();
            }
            map.put(key,value);
        }
        deque.addFirst(key);
    }

    public String get(Integer key){
        if(map.containsKey(key)){
            // remove from deque
            deque.remove(key);
            // Add to the deque
            deque.addFirst(key);
            return map.get(key);
        }
        return null;
    }
}
