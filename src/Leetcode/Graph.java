package Leetcode;

import java.util.*;

public class Graph {
    // No. of vertices
    private int V;

    // Adjacency List is ArrayList of ArrayList's
    private List<ArrayList<Integer>> adj;

    // Constructor
    Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    // Function to add an edge into the graph
    void addEdge(int start, int end) {
        adj.get(start).add(end);
    }

    public List<Integer> getDFSTraversal(){
        List<Integer> traversal= new ArrayList<>();
        //Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++){
            DFSUtil(visited, i, traversal);
        }
        return traversal;
    }

    public List<List<Integer>> getAdjacencyList()
    {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> vertex: adj) {
            result.add(new ArrayList<>(vertex));
        }
        return result;
    }

    private void DFSUtil(boolean[] visited, int vertex,List<Integer> traversal ){
        if(!visited[vertex]){
            traversal.add(vertex);
            visited[vertex] = true;
            for(int vertexi: adj.get(vertex)){
                DFSUtil(visited, vertexi, traversal);
            }
        }
    }

    public List<Integer> getBFSTraversal(){
        List<Integer> traversal = new ArrayList<>();
        List<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[V];
        queue.add(3);

        BFSUtil(visited, traversal, queue);

        return traversal;
    }
    private void BFSUtil(boolean[] visited, List<Integer> traversal, List<Integer> queue){
        while(!queue.isEmpty()){
            Integer head = queue.get(0);
            queue.remove(0);
            visited[head] = true;
            traversal.add(head);
            for(int vertexi: adj.get(head)) {
               if(!visited[vertexi]) {
                   queue.add(vertexi);
               }
            }
            BFSUtil(visited, traversal, queue);
        }
    }

    // Topological Sorting
    //We use a temporary stack.
    //We don’t print the vertex immediately,
    //We first recursively call topological sorting for all its adjacent vertices, then push it to a stack.
    //Finally, print the contents of the stack.

    public List<Integer> topologicalSortList(){
        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        //for (int i = V-1; i >=0; i--)
            //topologicalSortingUtil(visited, stack,i);
        for (int i =0;i< V ;i++) {
            topologicalSortingUtil(visited, stack, i);
        }

        while (!stack.isEmpty()){
            traversal.add(stack.pop());
        }
        return traversal;
    }

    private void topologicalSortingUtil(boolean[] visited, Stack<Integer> stack, Integer vertex){
        if(!visited[vertex]){
            visited[vertex] = true;
            for(int vertexi: adj.get(vertex)){
                topologicalSortingUtil( visited, stack, vertexi);
            }

            stack.add(vertex);
        }
    }

}
