package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//import javafx.util.Pair;

public class ConnectedGroups {
    private static void pushRelatedElements(int position, List<String> related, Stack<Integer> stack, boolean[] visited){
        char[] arr = related.get(position).toCharArray();
        for(int i = 0 ; i < related.size(); i++){
            if(!visited[i] && arr[i]=='1'){
                stack.push(i);
            }
        }
    }

    private static void DFSUtil( List<String> related, Stack<Integer> stack, boolean[] visited){
        // do it until stack is empty
        while (!stack.isEmpty()){
            // get top element , mark it as visited
            // push all the related elements
            int index =  stack.pop();
            visited[index] = true;
            pushRelatedElements(index, related, stack, visited);
        }
    }

    public static int connectedGroupSimple(List<String> related){
        int result =0;
        boolean[] visited = new boolean[related.size()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < related.size(); i++)
        {
            if(!visited[i]) {
                result++;
                stack.push(i);
                DFSUtil(related,stack,visited);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> related = new ArrayList<>();
        related.add("1100");
        related.add("1110");
        related.add("0110");
        related.add("0001");
        int res = connectedGroupSimple(related);
        System.out.println(res);

        related = new ArrayList<>();
        related.add("10010");
        related.add("01011");
        related.add("00110");
        related.add("10110");
        related.add("00001");
        res = connectedGroupSimple(related);
        System.out.println(res);
    }
}
