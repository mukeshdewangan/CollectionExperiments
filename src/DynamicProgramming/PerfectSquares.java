package DynamicProgramming;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PerfectSquares {
    public static void main(String[] args) {
        int sum = 200;
        getSquareRoot(sum);

        char input[] = {'0','0','0','0','0','0','0','0','0','0','0','0','0'};
        String a = "1";

        char[] target = a.toCharArray();
        System.out.println(minFlip( target));
    }
    private static void flipOperation(char[] input , int startIndex ){

        char[] firstPart = Arrays.copyOfRange(input,0,startIndex);
        int remainingLen = input.length - startIndex;
        char[] secondPart = new char[remainingLen];
        if(input[startIndex] == '1'){
            Arrays.fill(secondPart, '0');
        }
        else {
            Arrays.fill(secondPart, '1');
        }
        //concat first and second

    }

    private static int minFlip(char[] target){
        char current = '0';
        int count = 0;
        for (int i = 0; i < target.length; i++) {
            if(current != target[i]) {
                current = target[i];
                count++;
            }
        }
        return count;
    }

    private static void doubleArr(int[] input){
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i] *2;
        }
        //return input;
    }

    private static int getSquareRoot(int num){

        int sqrt =  (int)Math.sqrt(num);
        int remainder = num % (sqrt * sqrt);
        if(remainder == 0) return 1;

        int[] squares = new int[sqrt+1];
        for (int i = 0; i < squares.length ; i++) {
            squares[i] = i*i;
        }
        System.out.println( "Calling the minimumPerfectSquares()");
        int res = minimumPerfectSquaresDP(squares, num);
        System.out.println("Minimum perfect squares needed to make " + num + " is " + res);
        return  res;
    }

    private static int minimumPerfectSquares(int[] squares, int sum ){
        if(sum == 0) return 0;

        int minReqd = Integer.MAX_VALUE;

        for (int i = 1; i < squares.length ; i++) {
            if(squares[i] <= sum ){
                int tempMin = minimumPerfectSquares(squares, sum - squares[i]);
                if (tempMin != Integer.MAX_VALUE && tempMin + 1  < minReqd)
                     minReqd = tempMin + 1;
            }
        }
        System.out.println("For " + sum + " minReqd is " + minReqd );
        return minReqd;
    }

    private static int minimumPerfectSquaresDP(int[] squares, int sum){
        if(sum == 0 ) return 0;
        int[] minReqd = new int[sum+1];
        Arrays.fill(minReqd, Integer.MAX_VALUE);

        minReqd[0] = 0;
        for (int i = 1; i <= sum ; i++) {
            for (int j = 1; j < squares.length ; j++) {
                if( squares[j] <= i){
                    int tempMin =  minReqd[i - squares[j]];
                    if( tempMin != Integer.MAX_VALUE && tempMin + 1 < minReqd[i]){
                        minReqd[i] = tempMin + 1;
                    }
                }
            }
        }
        return minReqd[sum];
    }
}
