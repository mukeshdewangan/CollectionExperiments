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
        int[] intArr = {65, 32, 67,98, 13, 78,102, 9,69, 10};

        char[] charArr = new char[intArr.length];
        for (int i = 0; i < charArr.length; i++) {
            char c = (char) (intArr[i]);
            charArr[i] = c;
        }

        String str = new String(charArr) ;
        checkWhitespaceInString(str);
        containsWhitespace(str);
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

}
