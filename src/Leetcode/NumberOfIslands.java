package Leetcode;

//https://leetcode.com/problems/number-of-islands/description/
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','0','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','1','0'}
        };
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int count = numberOfIslands.numIslands(grid);
        System.out.println(count);
    }
    boolean[][] visited ;//= new boolean[m][n];
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        visited = new boolean[m][n];
        int count=0;
        for(int i=0;i< m ;i++) {
            for(int j=0;j< n ;j++) {
                if(!visited[i][j] && grid[i][j]=='1') {
                    findIsland(i, j, grid, m, n) ;
                    count++;
                }
            }
        }
        return count;
    }
    boolean findIsland(int i, int j, char[][] grid, int m, int n) {
        if(visited[i][j]) return false;
        visited[i][j] = true;
        boolean isIsland = false;

        if(i-1<m && i-1>=0 && j<n && j>=0 && grid[i-1][j]=='1') findIsland(i-1,j, grid, m, n);
        if(i+1<m && i+1>=0 && j<n && j>=0 && grid[i+1][j]=='1') findIsland(i+1,j, grid, m, n);
        if(i<m && i>=0 && j-1<n && j-1>=0 && grid[i][j-1]=='1') findIsland(i,j-1, grid, m, n);
        if(i<m && i>=0 && j+1<n && j+1>=0 && grid[i][j+1]=='1') findIsland(i,j+1, grid, m, n);
        return isIsland;
    }
}
