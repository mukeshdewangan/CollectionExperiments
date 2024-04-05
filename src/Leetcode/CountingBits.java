package Leetcode;

//https://leetcode.com/problems/counting-bits/
public class CountingBits {
    public static void main(String[] args) {
        CountingBits bits = new CountingBits();
        int[] res = bits.countBits(10);
        for (int i =0; i<res.length;i++) {
            System.out.println( i+":" + res[i]);
        }
    }
    public int[] countBits(int n) {
        int[] counts = new int[n+1];
        counts[0]=0;
        // for case n=0
        if(n==0) return counts;
        counts[1]=1;
        // for case n=1
        if(n==1) return counts;
        int currentMax =2;
        while(true){
            int counter = 0;
            while(currentMax+counter <= n && counter < currentMax){
                counts[currentMax+counter] = counts[counter] +1;
                counter++;
            }
            if(currentMax+counter > n)
                break;
            currentMax *=2;
        }

        return counts;
    }
}
