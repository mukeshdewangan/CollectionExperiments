package Leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUBasicImplementation<K,V> {
    final int size;

    //List<>
    public LRUBasicImplementation(int size) {
        this.size = size;
    }

    public static void main(String[] args) {
        int size = 5;
        LRUBasicImplementation lru = new LRUBasicImplementation(size);
    }


}
