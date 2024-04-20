package Leetcode;

public class ApolloTest2 {
    public static void main(String[] args) {
        String str = "12:30pm-12:00am";
        System.out.println( ApolloTest2.CountingMinutes(str));
        str = "1:23am-1:08am";
        System.out.println( ApolloTest2.CountingMinutes(str));
    }
    public static int CountingMinutes(String str) {
        String[] times = str.split("-");
        String first = times[0];
        String second = times[1];

        int firstMin = convertToMinutes(first);
        int secondMin = convertToMinutes(second);

        int diff = secondMin- firstMin;
        if(diff < 0) {
            diff += 1440;
        }
        return diff;
    }

   static int convertToMinutes(String str){
        int resultMinutes =0;
        if(str.endsWith("pm")) {
            resultMinutes+=720;
        }

        str = str.replace("am", "");
        str = str.replace("pm", "");

        int hours = Integer.parseInt(str.split(":")[0]);
        int minutes = Integer.parseInt(str.split(":")[1]);

        int temp = 60 * hours + minutes;
        return  resultMinutes + temp;
    }

}
