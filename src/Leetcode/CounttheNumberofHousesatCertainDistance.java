package Leetcode;

import java.util.*;

// https://leetcode.com/contest/weekly-contest-381/problems/count-the-number-of-houses-at-a-certain-distance-i/
public class CounttheNumberofHousesatCertainDistance {
    public static void main(String[] args) {
        CounttheNumberofHousesatCertainDistance c = new CounttheNumberofHousesatCertainDistance();
        //int[] res = c.countOfPairs(3,1,3);
        //int[] res = c.countOfPairs(4,1,1);
        int[] res = c.countOfPairs(5,2,4);//5, x = 2, y = 4
        //for (int i: res) {
            System.out.print(Arrays.toString(res));
        //}
    }

    public int[] countOfPairs(int n, int x, int y) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 1; i < n; ++i){
            List<Integer> l = new ArrayList<>();
            l.add(i+1);
            if(i==x) { l.add(y); }
            adj.put(i,l);
        }
        adj.put(n, new ArrayList<>());

        int[] kArray = new int[n+1];

        for(int node=1;node<=n;node++){
            boolean[] visited = new boolean[n+1];
            visited[node]=true;
            int currentK =0;
            getNumberOfNodesKDistance(node,adj,kArray, visited, currentK);
        }

        return kArray;
    }

    private void getNumberOfNodesKDistance(int currentNode, Map<Integer, List<Integer>> adj, int[] kArray,boolean[] visited, int currentK ){
        List<Integer> children = adj.getOrDefault(currentNode, new ArrayList<>());
        int kDistanceCount = 0;
        for (Integer childNode : children) {
            if(!visited[childNode]){
                visited[childNode] = true;
                kDistanceCount++;
                getNumberOfNodesKDistance(childNode, adj, kArray, visited, currentK+1);
            }
        }
        kArray[currentK+1] += kDistanceCount;
    }
}
