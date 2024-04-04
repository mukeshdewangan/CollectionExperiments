package Leetcode;

import java.util.*;

// Get earliest ancestor

// Given pair (parent,child)
/*
          10
           \
        1   2   4
        \  /   / \
          3   5   8
           \ /     \
            6       9
*/
public class Wayfair {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> adj = getIntegerListMap();

        for (Map.Entry<Integer, List<Integer>> entry: adj.entrySet() ) {
            System.out.println("child " + entry.getKey() + ":" + Arrays.toString(entry.getValue().toArray()));
        }

        Wayfair driver = new Wayfair();
        NodeDepthPair result = driver.getEarlierAncestor(11, adj,0);
        System.out.println( "Node " + result.node + " depth " + result.depth);
    }

    private static Map<Integer, List<Integer>> getIntegerListMap() {
        int[][] parentChild = new int[][]{{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{8,9},{10,2}, {9,11}};
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for( int[] pair: parentChild){
            int parent = pair[0];
            int child = pair[1];
            if(adj.containsKey(child)){
                List<Integer> parents = adj.get(child);
                parents.add(parent);
            }
            else{
                List<Integer> list = new ArrayList<>(); list.add(parent);
                adj.put(child, list);
            }
        }
        return adj;
    }

    static class NodeDepthPair{
        int node;
        int depth;
        NodeDepthPair(int n, int d){
            node = n; depth = d;
        }
    }

    NodeDepthPair getEarlierAncestor(int node, Map<Integer, List<Integer>> adj, int currentDepth){
        NodeDepthPair result = new NodeDepthPair(node, currentDepth);
        if(adj.containsKey(node)){
            List<Integer> parents = adj.get(node);
            for (Integer par: parents ) {
                NodeDepthPair parentNode = getEarlierAncestor(par, adj, currentDepth+1);
                if(parentNode.depth >= result.depth) {
                    result = new NodeDepthPair(parentNode.node, parentNode.depth);
                }
            }
        }
        return result;
    }

}
