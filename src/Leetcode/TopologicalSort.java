package Leetcode;

import java.util.List;

public class TopologicalSort {
    public static void main(String[] args) {
//        Graph graph = new Graph(6);
//        graph.addEdge(4,1);
//        graph.addEdge(4,0);
//        graph.addEdge(3,1);
//        graph.addEdge(2,3);
//        graph.addEdge(5,2);
//        graph.addEdge(5,0);

        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(2,0);
        graph.addEdge(0,3);
        graph.addEdge(2,4);
        graph.addEdge(3,2);


        // DFS
        List<Integer> dfsTraversal = graph.getDFSTraversal();
        System.out.println(" DFS");
        for (Integer i : dfsTraversal) {
            System.out.println( i + " ");
        }

        // BFS
        System.out.println(" BFS");

        List<Integer> bfsTraversal = graph.getBFSTraversal();
        for (Integer i : bfsTraversal) {
            System.out.println( i + " ");
        }


        // Topological Sorting
        System.out.println("Topological Soring");
        Graph tgraph = new Graph(7);
        tgraph.addEdge(6,1);
        tgraph.addEdge(6,5);
        tgraph.addEdge(5,3);
        tgraph.addEdge(5,4);
        tgraph.addEdge(1,2);
        tgraph.addEdge(1,5);
        tgraph.addEdge(0,1);
        tgraph.addEdge(0,2);
        tgraph.addEdge(2,3);

        List<Integer> topologicalSortTraversal = tgraph.topologicalSortList();
        for (Integer i : topologicalSortTraversal) {
            System.out.println( i + " ");
        }
    }
}
