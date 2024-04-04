package Leetcode;

public class NumberOfFullRounds {

    private static int numberOfFullRounds(String startTime, String finishTime ){
        int start = convertStringTimeToInt(startTime);
        int finish = convertStringTimeToInt(finishTime);
        int res =0;
        // get index of number equals to or greater than start => x

        int[] intervals = generateIntervalArray(15,24);
        int x = getGreaterOrEquals(intervals,start);
        int y = getSmallerOrEquals(intervals,finish);
        // get index of number equals to or smaller than finish => y
        if(finish < start){
            // res = size-x+1+y
            res = intervals.length - x + y;
        }
        else{
            // res = y-x+1
            res = y-x;
        }
        return res;
    }

    private static int convertStringTimeToInt(String time){
        String[] durations = time.split(":");
        int hour = Integer.parseInt(durations[0]);
        int min = Integer.parseInt(durations[1]);
        return hour*60 + min;
    }

    private static int getGreaterOrEquals(int[] intervals, int element){ // Modified Binary search
        int low =0; int high = intervals.length-1;
        int result = intervals.length;
        while(low <= high){
            int mid  = ((high-low))/2 + low;
            if( element <= intervals[mid]) {
                result = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return result;
    }

    private static int getSmallerOrEquals(int[] intervals, int element){ // Modified Binary search
        int greater = getGreaterOrEquals(intervals, element);
        if(greater == intervals.length ){
            return intervals.length - 1;
        }

        if(element == intervals[greater]){
            return greater;
        }
        else
            return greater - 1;
    }

    private static int[] generateIntervalArray(int duration, int hours){
        int size = (60/duration)*hours;
        int[] intervals = new int[96];

        for(int i=0;i< size;i++){
            intervals[i] = i*duration;
        }
        return intervals;
    }

    private void testGetEqualsOrGreater(){
        int[] intervals = generateIntervalArray(15, 24);

        int[] se = {34, 78,1443, 299, 301,300, 0,1};

        for (int i = 0; i < se.length; i++) {
            int index =  getGreaterOrEquals(intervals,se[i]);
            if(index == intervals.length){
                System.out.println("No bigger element present in the element");
            }
            else
                System.out.println( "Searching  "+ se[i] + ": Found at index " + index+ " with values at " + intervals[index]);
        }

        System.out.println("________________________");
        for (int i = 0; i < se.length; i++) {
            int index =  getSmallerOrEquals(intervals,se[i]);
            if(index == intervals.length){
                System.out.println("No bigger element present in the element");
            }
            else
                System.out.println( "Searching  "+ se[i] + ": Found at index " + index+ " with values at " + intervals[index]);
        }
    }
    public static void main(String[] args) {
        int ans = numberOfFullRounds("05:23", "06:46");
        System.out.println(ans);
        ans = numberOfFullRounds("22:03", "03:23");
        System.out.println(ans);

        ans = numberOfFullRounds("12:01", "12:44");
        System.out.println(ans);
        ans = numberOfFullRounds("20:00", "06:00");
        System.out.println(ans);
        ans = numberOfFullRounds("00:00", "23:59");
        System.out.println(ans);
        ans = numberOfFullRounds("12:46", "12:47");
        System.out.println(ans);
    }

}
