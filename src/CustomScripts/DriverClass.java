package CustomScripts;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DriverClass extends ResetMarketoCursor {
    // ?: zero or one (optional),
    // +: one or more (1+),
    // *: zero or more (0+),
    // Decimal point is mandatory to be inferred as Double (E notation is optional)
    private static final Pattern DOUBLE = Pattern.compile("[+-]?(?:(?:\\d*\\.\\d+(?:[eE][+-]?\\d+)?)|(?:\\d+\\.\\d*))");

    // New RE
    //private static final Pattern DOUBLE_N = Pattern.compile("[+-]?(?:(?:\\d*[.]?\\d*(?:[eE][+-]?\\d+)?)|(?:\\d+\\.\\d*))");
    private static boolean isDouble(String value) {
        return ((DOUBLE.matcher(value).matches()/* && notLoosingPrecision(value)*/)|| isSpecialNonNumericalValue(value));
    }

    private static boolean isSpecialNonNumericalValue(String value) {
        // TODO there will need to be more values in here, like negative infinity, etc
        // TODO but until we find them, we're going to stick with the minimal set that we've actually seen
        switch (value) {
            case ("Infinity"):
            case ("-Infinity"):
            case ("NaN"):
                return true;
            default:
                return false;
        }
    }


    private static boolean parseStringToDouble( String str){
       // boolean flag = isDouble(str);
        //System.out.println(String.format( "isDouble(%s) = %s", str, flag));
        //double d = Double.parseDouble(str);
        // System.out.println(d);
        return isDouble(str);
    }

    public static void main(String[] args) {
        parseStringToDoubleExperiment();
        //test_removeLeadingAndTrailingZeroes();
        //String str = "0.110000";
        //Double d = Double.parseDouble( str );
        //System.out.println(d);
        //checkIfDoubleParsingLossesPrecision();
    }

    private static void test_removeLeadingAndTrailingZeroes(){
        String[] strings = new String[6];
        strings[0] = "00090090900";
        strings[1] = "00000";
        strings[2] = "";
        strings[3] = "0";
        strings[4] = "000900909";
        strings[5] = "12345900909";
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i] + " : " + removeLeadingAndTrailingZeroes(strings[i]));
        }
    }

    private static String removeLeadingAndTrailingZeroes(String str){
        if(str.isEmpty()) return str;
        int start = 0;

        while (start < str.length()-1 && str.charAt(start) == '0'){
            start++;
        }
        int end = str.length()-1;
        while (end > start && str.charAt(end) == '0'){
            end--;
        }

        String subString = str.substring(start, end+1);
        return subString;
    }

    static boolean notLoosingPrecision(String value){
        // Get the original string and after Double.parseDouble();
        double d = Double.parseDouble(value);
        System.out.println("Initial: "+ value + " Parsed : " + d);
        // Split the string with 'e'/'E' as delimiter
        String[] splitFirstPart = value.split("[eE]");
        String[] splitFirstPartParsed = Double.toString(d).split("[eE]");

        // Take the first part
        String firstIndexInitial = splitFirstPart[0];
        String firstIndexParsed =  splitFirstPartParsed[0];

        // Remove decimal
        firstIndexInitial = firstIndexInitial.replace(".","");
        firstIndexParsed = firstIndexParsed.replace(".","");

        firstIndexInitial = firstIndexInitial.replaceFirst("^0+(?!$)", "");
        firstIndexParsed = firstIndexParsed.replaceFirst("^0+(?!$)", "");
        // If first part lengths are unequal -> the loss in precision
        if( firstIndexParsed.length() > firstIndexInitial.length()) return true;
        if(firstIndexInitial.length() != firstIndexParsed.length())
            return false;

        // If equal then every elements should be equal -> // Particularly in case of Rounding OFF
        return firstIndexInitial.charAt(firstIndexInitial.length()-1) != firstIndexParsed.charAt( firstIndexParsed.length() -1);

        // Arrays.equals(array1, array2); -> it is inefficient since it iterates from beginning,
        // we can arrive quicker at decision when we iterate from end. (Mostly it is because of )
    }

    static void parseStringToDoubleExperiment(){
        String[] loop = new String[29];
        {
            loop[0] = "1.53846153846153846153846153846153846154";
            loop[1] = "-123.4567890123456789";
            loop[2] = "-123.456789012345678e304";
            loop[3] = "123456789012345678.12345";
            loop[4] = "-1.2345678901234567E50"; // consider 18 characters excluding '-' and E50

            loop[5] = "-123.45678901234567E50"; // consider 18 characters excluding '-' and E50
            loop[6] = "9.99999e308";

            loop[7] = "1.734e308";
            loop[8] = "1.834e308";
            loop[9] = "173.4e308";
            loop[10] = "-1.23432545465645567E309";
            loop[11] = "123.432545465645567E300";
            loop[12] = "123.456789012345678E306";

            loop[13] = "-Infinity";
            loop[14] = "Infinity";
            loop[15] = "12.23e308";

            loop[16] = "1.";
            loop[17] = "0.12345e23";
            loop[18] = ".12345";
            loop[19] = "0.0012345e23";
            loop[20] = "0.000000012345e23";
            loop[21] = "000000095.00";
            loop[22] = "0.00"; // remove leading and trailing 0's
            loop[23] = "+1E-9";
            loop[24] = "20E+12";
            loop[25] = ".E-13";
            loop[26] = "1.E133";
            loop[27] = "2E22130289";
            loop[28] = "-123E12";

        }
        for ( int i =0; i < loop.length; i++) {
            System.out.println( loop[i] + " : "+ isDouble(loop[i]));
        }

        /*
        System.out.println("Start time ");
        for ( int i =0; i < loop.length; i++) {
            System.out.println( loop[i] + " : "+ parseStringToDouble(loop[i]));
        }*/
    }
}
