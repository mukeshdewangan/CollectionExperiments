package Leetcode;

import java.time.LocalDate;
import java.util.*;

// https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
public class DetectCycle {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> adj = getIntegerListMap();
        for (Map.Entry<Integer, List<Integer>> entry: adj.entrySet() ) {
            System.out.println("child " + entry.getKey() + ":" + Arrays.toString(entry.getValue().toArray()));
        }
        StackUsingList<Integer> stack = new StackUsingList<Integer>();
        stack.push(43);
        stack.push(21);
        stack.push(9);
        stack.pop();
        stack.pop();
        stack.push(76);
        stack.push(55);

        System.out.printf("stack contains %d %b%n", 34 ,stack.contains(34));
        System.out.printf("stack contains %d %b%n", 55 ,stack.contains(55));
        while(!stack.isEmpty()){
            System.out.println(stack.peek());
            stack.pop();
        }
    }

    static class StackUsingList<T>{
        LinkedList<T> list = new LinkedList<>();
        void push(T val){
            list.add(val);
        }
        boolean contains(T val){
            return list.contains(val);
        }

        boolean pop(){
            if(list.isEmpty()) return false;
            list.remove(list.size()-1);
            return true;
        }

        T peek(){
            if(list.isEmpty()) throw new NullPointerException("Stack is empty");
            else return list.get(list.size()-1);
        }

        boolean isEmpty(){
            return list.isEmpty();
        }
    }

    private static Map<Integer, List<Integer>> getIntegerListMap() {
        int[][] parentChild = new int[][]{{2,1},{2,0},{1,0},{3,2}};
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



    private static boolean containsCycle(Map<Integer, List<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        LocalDate localDate = LocalDate.parse("2023-01-01");
        System.out.println(localDate);
        return true;
    }

    static void DFSUtilTopological(Integer node, Map<Integer, List<Integer>> adj, boolean[] visited, List<Integer> res){
        if(visited[node]) return;
        visited[node] = true;
        // For DFS
        //res.add(node);
        for(Integer child: adj.get(node)) {
            DFSUtilTopological(child, adj, visited, res);
        }
        // For topological sort
        res.add(node);
    }
    static List<Integer> topologicalSort(Map<Integer, List<Integer>> adj){
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[6];
        for(int i=5;i>0;i--)
            DFSUtilTopological(i,adj, visited, res);
        return res;
    }

// For topological sorting
//    public static void main(String[] args) {
//        Map<Integer, List<Integer>> adj = getAdjacencyList();
//        List<Integer> sorted = topologicalSort(adj);
//        System.out.println(sorted);
//    }

    private static Map<Integer, List<Integer>> getAdjacencyList(){
        Map<Integer, List<Integer>> adj = new HashMap<>();
        adj.put(1, List.of(2,5));
        adj.put(2, List.of(5));
        adj.put(3, List.of(2));
        adj.put(4, List.of(3,5));
        adj.put(5, Collections.emptyList());
        return adj;
    }



}
