package Leetcode;

import java.util.Arrays;

public class MaximumPointFromCoins {

    private static int maxScoreInternal(int[] cardPoints, int k, int start, int end, int[][] maxPoints){
        if(k<=0) return 0;

//        if(maxPoints[k] != -1)
//            return maxPoints[k];

       // if (end>=0 && start <= cardPoints.length -1) {
            // take the card from front
            int fromBegin = cardPoints[start] + maxScoreInternal(cardPoints, k - 1, start + 1, end, maxPoints);
            //System.out.println( "Pick from Begin when k=" + k + " start+1 "+ (start+1 )+   );
            // take card from last
            int fromEnd = cardPoints[end] + maxScoreInternal(cardPoints, k - 1, start, end - 1, maxPoints);

            maxPoints[start][end] = Math.max(maxPoints[start][end], Math.max(fromBegin, fromEnd));
        //}
        //return maxPoints[k];
        return Math.max(fromBegin, fromEnd);
    }

    private static int maxScore_1(int[] cardPoints, int k){

        int len = cardPoints.length;
        int[] preSumLeft = new int[k+1];
        int[] preSumRight = new int[k+1];

        preSumLeft[0] = 0; //cardPoints[0];
        preSumRight[0] = 0;//cardPoints[cardPoints.length-1];

        for (int i = 1; i <= k; i++) {
            preSumLeft[i] = preSumLeft[i-1] + cardPoints[i-1];
        }
        for (int i = 1; i <= k; i++) {
            preSumRight[i] = preSumRight[i-1] + cardPoints[len-i];
        }

        int maxSum = 0;
        for(int i = 0; i <= k; i++){
             int sum =  preSumLeft[i]+ preSumRight[k-i];
             maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    private static int maxScore(int[] cardPoints, int k){
        int len = cardPoints.length;
        int[][] maxPoints = new int[len][len];
        for (int i = 0; i < len  ; i++) {
            for (int j = 0; j < len; j++) {
                maxPoints[i][j]=-1;
            }
        }

        int result =  maxScoreInternal(cardPoints, k, 0, cardPoints.length-1, maxPoints);
        return result;
    }
    public static void main(String[] args) {
        int[] input = {1,79,80, 2, 1, 200,1};
        int k = 3;

        Arrays.sort(input);

        System.out.println(Arrays.toString(input));
        //input = new int[]{9, 8, 2, 0, 1, 50, 2, 4, 1};
        //k = 4;
        //int res = maxScore(input,k);
        int res = maxScore_1(input,k);
        System.out.println(res);
    }
}
