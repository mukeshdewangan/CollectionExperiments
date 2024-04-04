package InternInterview;

import refactor.test.PredicateTest;
import java.util.List;
import java.util.function.Predicate;

public class ContiguousArray {
    static class TestPredicate implements Predicate<String>{
        @Override
        public boolean test(String s) {
            return isGreaterThanFive().or(isGreaterThanEight()).or(isGreaterThanTwleve()).test(s);
        }

        public Predicate<String> isGreaterThanFive(){
            return (input) -> {
                if (input.length() < 5){
                System.out.println("isGreaterThanFive returning true");
                return true;
            }
                return false;
            };
        }

        public Predicate<String> isGreaterThanEight(){
            return (input) -> { if (input.length() > 8){
                System.out.println("isGreaterThanEight returning true");
                return true;
            }
                return false;
            };
        }
        public Predicate<String> isGreaterThanTwleve(){
            return (input) -> {
                if (input.length() > 12){
                System.out.println("isGreaterThanTwleve returning true");
                return true;
            }
                return false;
            };
        }
    }

    public static void main(String[] args) {
        String input="input6";
        TestPredicate p = new TestPredicate();
        boolean result = p.test(input);
        System.out.println(" result "+ result);
    }

    public static String palindromeChecker(String s, List<Integer> startIndex, List<Integer> endIndex, List<Integer> subs) {
        // Write your code here

        int len = s.length();
        char[] finalAns = new char[subs.size()];
        int[][] preCompute = new int[26][len + 1];
        char[] input = s.toCharArray();
        // Populate the 26*len+1 array
        for (int i = 0; i < len; i++) {
            int cha = input[i] - 'a';
            for (int k = 0; k < 26; k++) {
                preCompute[k][i + 1] = preCompute[k][i];
            }
            preCompute[cha][i + 1]++;
        }
        return "";
    }
}
