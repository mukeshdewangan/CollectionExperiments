package Leetcode;


import jnr.ffi.annotations.In;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class IfGraphIsConnected {
    static Graph graph;
    public static void main(String[] args) {
        graph = new Graph(5);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        List<Integer> ints = graph.getDFSTraversal();
        ints.forEach(System.out::println);
        List<List<Integer>> list = invertDirection(graph);
    }

    public static List<List<Integer>> invertDirection(Graph graph){
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> adjList = graph.getAdjacencyList();
        for (int i=0;i< adjList.size();i++) {
            List<Integer> innerList = adjList.get(i);
            if(!innerList.isEmpty()) {
                for (Integer integer: innerList) {
                    List<Integer> in = result.get(integer);
                    if(in==null){
                        in = new ArrayList<>();
                    }

                }


                // adjList.add(new ArrayList<>(innerList));
                // adjList.
            }
        }
        return result;
    }
}
