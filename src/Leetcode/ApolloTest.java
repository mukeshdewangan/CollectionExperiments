package Leetcode;

public class ApolloTest {
    public static void main (String[] args) {
        // keep this function call here
        //Scanner s = new Scanner(System.in);
        //System.out.print(MinWindowSubstring(s.nextLine()));
        String str1 = "ahffaksfajeeubsne";
        String str2 = "jefaa";
        System.out.println( MinWindowSubstring( new String[]{ str1, str2}));

    }
    public static String MinWindowSubstring(String[] strArr) {
        int[] freqMap = new int[26];
        for(char ch : strArr[1].toCharArray()){
            freqMap[ch-'a']++;
        }

        char[] mainStr = strArr[0].toCharArray();
        int[] mainStrFreq = new int[26];
        int index = 0;
        while(index < strArr[1].length()){
            char ch = mainStr[index];
            mainStrFreq[ch-'a']++;
            index++;
        }

        int start = 0;
        int end = index-1;

        String result = strArr[0];
        System.out.println( "start" + start + "  end " + end);
        while(start <= end && end < mainStrFreq.length){
            if(containsAllChars(mainStrFreq, freqMap)){
                System.out.println(" Contains all chars current length "+ (end-start+1) + " str " + strArr[0].substring(start,end+1));
                if(result.length() > (end-start+1)){
                    result = strArr[0].substring(start,end+1);
                }
                // increment start

                mainStrFreq[mainStr[start] -'a']--;
                start++;
                continue;
            }
            // increment end
            if(end+1< mainStr.length) {
                char ch = mainStr[++end];
                mainStrFreq[ch - 'a']++;
            }else break;
        }
        // code goes here
        return result;
    }

    private static boolean containsAllChars(int[] strFreqMap, int[] freqMap ){
        for(int i=0;i<26;i++) {
            if (strFreqMap[i] < freqMap[i]) {
                return false;
            }
        }
        return true;
    }


}
