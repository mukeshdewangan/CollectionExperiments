package PerfectSubString;

public class PerfectSubstringHR {
    public static boolean checkIfAllDigitsHaveKOccurance( int[] occurance, int k ){
        for (int i = 0; i < occurance.length ; i++) {
            if(occurance[i] != 0 && occurance[i] != k) return false;
        }
        return true;
    }

    public static int perfectString(String word , int k){
        int result =0;
        if(k==1) return word.length();
        //int[] occurrence = new int[10];

        for (int i = 0; i < word.length(); i++) {
            int charValue = word.charAt(i) - '0';
            int[] occurrence = new int[10];
            occurrence[charValue]++;
            for (int j = i+1 ; j < word.length() ; j++) {
                int charJValue = word.charAt(j) - '0';
                occurrence[charJValue]++;
                if(checkIfAllDigitsHaveKOccurance(occurrence,k)){
                    System.out.println(word.substring(i,j+1));
                    result++;
                }
            }
            //occurrence[charValue]--;
        }
        return result;
    }

    public static void main(String[] args) {
        String word = "1001";
        int k =2;
        int res = perfectString(word,k);
        System.out.println("Total perfect string for " + word + " is "+ res);
    }
}
