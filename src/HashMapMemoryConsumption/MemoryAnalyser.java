package HashMapMemoryConsumption;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MemoryAnalyser {
    public static void main(String[] args) {
        int initialCapacity = 100; // Initial capacity of the HashMap
        int entries = 100; // Number of entries to analyze

        // Create a HashMap
        Map< String, Instant> hashMap = new HashMap<>(initialCapacity);

        Map<String, Map< String, Instant>> lastModified = new HashMap<>();
        String tableName = "ABC";
        String tableName2 = "LMN";
        lastModified.computeIfAbsent(tableName, t-> new HashMap<>());
        lastModified.computeIfAbsent(tableName, t-> new HashMap<>());

        Map< String, Instant> latestModified ;
        if(lastModified.containsKey(tableName)) {
            latestModified = lastModified.get(tableName);
            latestModified.put("record1", Instant.parse("2023-02-13T21:03:12Z"));
            latestModified.put("record2", Instant.parse("2023-04-13T21:03:12Z"));
            latestModified.put("record3", Instant.parse("2021-09-13T21:03:12Z"));
        }

        Random random = new Random();
        // Add entries to the HashMap
//        for (int i = 0; i < entries; i++) {
//            hashMap.put( String.valueOf (System.currentTimeMillis() + random.nextInt()), Instant.now().minus(random.nextInt(), ChronoUnit.DAYS));
//        }
//
//        cleanMap(lastModified, tableName2);
//        cleanMap(lastModified, tableName);
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Perform a deep clone of the object to isolate its memory footprint
        // You can use your own deep clone mechanism here
        //Object clone = deepCloneStr(object);
        List<String> list = new ArrayList<>();
        for (int i =0 ; i< 1000_000;i++){
            list.add("acc_" + UUID.randomUUID());
        }
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println( memoryAfter - memoryBefore);



        //


        // Measure memory consumption


        //long memoryConsumption = getMemoryConsumption(hashMap)/1000;
        long memoryConsumption = getMemoryConsumptionStr(list)/1000;
        //System.out.println("Memory consumption for " + entries + " entries: " + memoryConsumption + " kilobytes");
        System.out.println("Memory consumption for " + list.size() + " entries: " + memoryConsumption/1000 + " megabytes");
    }

    private static void cleanMap(Map<String, Map<String, Instant>> lastModifiedMap, String tableName){
        Map<String, Instant> lastMap = lastModifiedMap.getOrDefault(tableName , new HashMap<>());
        lastMap.clear();
    }

    private static long getMemoryConsumption(Map<String, Instant> object) {
        Runtime runtime = Runtime.getRuntime();
        //runtime.gc(); // Run garbage collector to minimize interference
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Perform a deep clone of the object to isolate its memory footprint
        // You can use your own deep clone mechanism here
        Object clone = deepClone(object);

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        return memoryAfter - memoryBefore;
    }


    private static long getMemoryConsumptionStr(List<String> object) {
        Runtime runtime = Runtime.getRuntime();
        //runtime.gc(); // Run garbage collector to minimize interference
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Perform a deep clone of the object to isolate its memory footprint
        // You can use your own deep clone mechanism here
        Object clone = deepCloneStr(object);

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        return memoryAfter - memoryBefore;
    }

    private static Object deepCloneStr(List<String> list) {
        List<String> cloned = new ArrayList<>();
        // Implement your deep cloning logic
        for (String str:list){
            cloned.add(str);
        }

        return cloned;
    }
    // Implement your own deep clone mechanism here
    private static Object deepClone(Map<String, Instant> map) {
        Map<String, Instant> cloned = new HashMap<>();
        // Implement your deep cloning logic
        for (Map.Entry<String, Instant> entry: map.entrySet()){
            cloned.put(entry.getKey(), entry.getValue());
        }

        return cloned;
    }
}

