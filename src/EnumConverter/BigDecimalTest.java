package EnumConverter;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal res;

        // For user input
        // Use Scanner or BufferedReader

        // Object of String created
        // Holds the value
        String input1
                = "31452678569.25";

        // Convert the string input to BigDecimal
        BigDecimal a
                = new BigDecimal(input1);

        // Scale for BigDecimal
        int newScale = 4;

        // Using setScale() method
        res = a.setScale(newScale);


        // Display the result in BigDecimal
        System.out.println(res);
    }

}
