package Leetcode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

// Generate all 4 digits numbers using DFS - recursion
// Extension of this problem is given a lock with 4 digit secret number and certain blocked numbers,
// find the secret number of the lock
public class Generate4DigitNumbersUsingDFS {
    public static void main(String[] args) {
        String str = "2023-09-01";

        Set<String> requests = new HashSet<>();
        requests.add("abc");
        requests.remove("xyz");


        boolean res = isValidDateFormat(str);
        System.out.println(res);
    }

    public static boolean isValidDateFormat(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        try {
            // Try to parse the input string with the specified format
            LocalDate date = LocalDate.parse(input, dateFormatter);
            return true; // If parsing is successful, the format is valid
        } catch (Exception e) {
            return false; // Parsing failed, the format is invalid
        }
    }
}
