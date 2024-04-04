package MultithreadingStateMutation;


import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public synchronized V get(K key) {
        return cache.get(key);
    }

    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    public synchronized boolean contains(K key){
        return cache.containsKey(key);
    }

    public synchronized void printCache() {
        for (Map.Entry<K, V> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);

        lruCache.put("A", 1);
        lruCache.put("B", 2);
        lruCache.put("C", 3);

        lruCache.printCache();  // Output: A: 1, B: 2, C: 3
        System.out.println("-----");
        lruCache.get("A");
        lruCache.get("B");
        lruCache.get("C");
        lruCache.put("D", 4);
        lruCache.put("E", 2);
        System.out.println(lruCache.contains("E"));
        lruCache.printCache();  // Output: B: 2, C: 3, A: 1
    }
}

