package HashMapMemoryConsumption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class MemoryAnalyzerV2 {
    public static void main(String[] args) {
        int initialCapacity = 100; // Initial capacity of the HashMap
        int entries = 1000; // Number of entries to analyze

        // Create a HashMap
        HashMap<String, Instant> hashMap = new HashMap<>(initialCapacity);

        Random random = new Random();

        // Add entries to the HashMap
        for (int i = 0; i < entries; i++) {
            String s = System.currentTimeMillis() + UUID.randomUUID().toString();;
            hashMap.put(s, Instant.now().minus(random.nextInt(), ChronoUnit.DAYS) );
        }

        // Measure memory consumption
        long memoryConsumption = measureMemoryConsumption(hashMap)/1000;
        System.out.println("Memory consumption for " + entries + " entries: " + memoryConsumption + " KBs");
    }

    private static long measureMemoryConsumption(HashMap<?, ?> hashMap) {
        // Calculate the size of the HashMap using the approximate memory usage factors
        int entrySize = 16 + 4 + 4 + 4; // Object overhead + hash code + key reference + value reference

        // Calculate the size of each entry in the HashMap
        long entriesSize = (long) hashMap.size() * entrySize;

        // Calculate the size of the internal array (buckets)
        //long arraySize = (long) hashMap.capacity() * 4; // Assuming 4 bytes per reference
        long arraySize = (long) hashMap.size() * 4; // Assuming 4 bytes per reference

        // Calculate the total memory consumption
        long memoryConsumption = entriesSize + arraySize;

        return memoryConsumption;
    }
}
