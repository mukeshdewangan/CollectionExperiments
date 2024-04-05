package InternInterview;

import java.util.*;

public class SocialMediaConnections {
    public static void main(String[] args) {
        //int friendsNodes = 6;
        //List<Integer> friendsFrom = Arrays.asList(1, 2, 2, 3, 4, 5);
        //List<Integer> friendsTo = Arrays.asList(2, 4, 5, 5, 5, 6);
//        int friendsNodes = 5;
//        List<Integer> friendsFrom = Arrays.asList(1, 1, 2, 2, 3, 4);
//        List<Integer> friendsTo = Arrays.asList(2, 3, 3, 4, 4 ,5);
//        bestTrio(friendsNodes, friendsFrom, friendsTo);
        int friendsNodes = 10;
        List<Integer> friendsFrom = Arrays.asList(1,1,1,1,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,5,6,6,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10);
        List<Integer> friendsTo = Arrays.asList(2,6,7,10,5,6,7,8,2,6,7,8,9,10,1,2,7,8,10,1,3,4,6,9,10,4,7,5,8,9,1,5,6,10,2,4,6,8, 2, 6, 7, 9);
        bestTrio(friendsNodes, friendsFrom, friendsTo);
    }

    public static int bestTrio(int friendsNodes, List<Integer> friendsFrom, List<Integer> friendsTo) {
        Map<Integer,Set<Integer>> map = new HashMap<>();

        int len = friendsFrom.size();
        for(int i = 0; i < friendsNodes; i++){
            Set<Integer> tempSet = new HashSet<>();
            //tempSet.add(i+1);
            map.put(i+1,tempSet);
        }

        for(int i = 0; i < len ; i++){
            Integer from = friendsFrom.get(i);
            Integer to = friendsTo.get(i);
            Set<Integer> fromSet = map.get(from);
            Set<Integer> toSet = map.get(to);

            fromSet.add(to);
            toSet.add(from);

            map.put(from,fromSet);
            map.put(to,toSet);
        }

        /*
        for (Map.Entry<Integer, Set<Integer>> entry: map.entrySet() ) {
            System.out.println(entry.getKey() + " : " +  entry.getValue());
        }*/

        Map<Integer,Set<Integer>> finalMap = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry: map.entrySet()) {
            if(entry.getValue().size() >= 2)
            {
                Set<Integer> set = entry.getValue();
                set.add(entry.getKey());
                finalMap.put(entry.getKey(),set);
            }
        }

        for (Map.Entry<Integer, Set<Integer>> entry: finalMap.entrySet()) {
            System.out.println(entry.getKey() + " : " +  entry.getValue());
        }
        int minTrioSum = -1;
        if(finalMap.size() >=3 )
            minTrioSum = Math.max(minTrioSum,findMinTrios(new ArrayList<>(finalMap.values())));

        System.out.println("Minimum sum trio " + minTrioSum);
        return minTrioSum;
    }

    private static int findMinTrios(List<Set<Integer>> finalConnections){
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < finalConnections.size()-2; i++){
            for (int j = i+1; j < finalConnections.size()-1; j++) {
                Set<Integer> first = finalConnections.get(i);
                Set<Integer> second = finalConnections.get(j);
                Set<Integer> intersectSet = new HashSet<>(first);
                intersectSet.retainAll(second);
                if(intersectSet.size() >= 3){
                    for (int k = j+1; k < finalConnections.size(); k++) {
                        Set<Integer> intersectSet2 = new HashSet<>(intersectSet);
                        Set<Integer> third = finalConnections.get(k);
                        intersectSet2.retainAll(third);
                        //System.out.println("intersectSet " + intersectSet2);
                        if(intersectSet2.size() >= 3){
                            //System.out.println("Trio FOUND");
                            int sum = first.size() - 3 + second.size() - 3 + third.size() -3;
                            System.out.println( i+ "," + j + "," + k +" : " + sum);
                            min = Math.min(min, sum);
                        }
                    }
                }
            }
        }
        return min;
    }
}
