package BinarySearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3,6,7,13,19};
        int target = 2;
        //System.out.println(arr[searchItemJustGreater(arr, target)]);
        float fl = (float) 1/2;
        //System.out.println(fl);
        //int index =  searchCompetitive(arr,target);
        //System.out.println(arr[index]);
        // 32 - space
        // 9 - tab
        // 10 - \n - new line
        // 13 - \r - carriage return
        int[] arr2 = {3,6,9,12,15,16};
        int[] intArr = {65, 32, 67,98, 13, 78,102, 9,69, 10};

        System.out.println( searchCompetitive(arr2, 20));

//        char[] charArr = new char[intArr.length];
//        for (int i = 0; i < charArr.length; i++) {
//            char c = (char) (intArr[i]);
//            charArr[i] = c;
//        }
//
//        String str = new String(charArr) ;
//        checkWhitespaceInString(str);
//        containsWhitespace(str);
    }

    private  static boolean containsWhitespace(String str){
        //boolean containsWhitespace = false;
        char[] chars =  str.toCharArray();
        for ( int i = 0;i< chars.length ; i++){
            char c = chars[i];
            //System.out.println("character " + c);
            boolean isWhitespace = Character.isWhitespace(c);
            if(isWhitespace){
                System.out.println("contains whitespace at index "+ i );
                return true;
            }
        }
        return false;
        //return containsWhitespace;
    }

    private static boolean checkWhitespaceInString(String apiKey) {
        int index = 0;
        boolean nowhiteSpace = true;
        for (char c : apiKey.toCharArray()) {
            switch (c) {
                case ' ':
                    System.out.println("Stripe Validator: Space found in API key at " + index);
                    nowhiteSpace = false;
                    break;
                case '\t':
                    System.out.println("Stripe Validator: Tab found in API key at " + index);
                    nowhiteSpace = false;
                    break;
                case '\n':
                    System.out.println("Stripe Validator: Newline found in API key at " + index);
                    nowhiteSpace = false;
                    break;
                case '\r':
                    System.out.println("Stripe Validator: Return found in API key at " + index);
                    nowhiteSpace = false;
                    break;
                default:
            }
            index++;
        }
        return nowhiteSpace;
    }


    /**
     * Searches element equal to or greater than search element, val
     * @param arr
     * @param val
     * @return
     */

    private static int searchCompetitive(int[] arr, int val){
        int min = 0 ; int max = arr.length-1;

        while (min< max) {
            int mid = min + (max - min) / 2;
            if(arr[mid]< val)
                min = mid + 1;
            else
                max = mid;
        }
        if(arr[min] < val) return -1;
        return min;
    }

    private static int simpleBinarySearch(int[] arr , int num){
        int start = 0;
        int end = arr.length-1;

        while(start <= end){
            int mid = (end-start)/2 + start;
            if(num == arr[mid])
                return mid;
            else if(arr[mid] > num){
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        return -1;
    }
    public static int searchItemJustGreater(int[] arr, int target){
        int result =-1;
        int low = 0; int high = arr.length-1;

        while (low<=high){
            int mid = low + (high - low)/2;
            if(arr[mid] == target) return  mid;
            if( arr[mid] > target){
                high = mid-1;
                result = mid;
            }else {
                low = mid + 1;
            }
        }
        return result;
    }

    // Binary search
    private long greaterThanOrEqualTo(long[] prefixSum, long num){
        int start = 0;
        int end = prefixSum.length-1;
        int result = end;
        // 3, 8 ,11, 13
        // start = 0 end = 3 , num =  6  , mid = 1

        boolean found = false;
        while(start<end){
            int mid = start + (end-start)/2;
            if(prefixSum[mid] == num){
                result = mid;
                found = true;
                break;
            }
            if(num < prefixSum[mid]){
                result = end;
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        if(!found && result == 0) result = start+1;
        //result
        return prefixSum[result];
    }

}
