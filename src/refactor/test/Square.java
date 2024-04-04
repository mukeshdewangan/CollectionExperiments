package refactor.test;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Square {
    public static void main(String[] args) throws IOException {

        int i = -4;
        int res = i %2;
        System.out.println(" mod  " + res);
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");


        Predicate<String> predicate1 =  str -> str.startsWith("A");
        Predicate<String> predicate2 =  str -> str.length() < 5;

        Set<String> s = new HashSet<>();

        List<String> result = names.stream()
                .filter(predicate1.and(predicate2))
                .collect(Collectors.toList());

        System.out.println( "result size " + result.size() );
        System.out.println(result.contains("Adam"));

        /*
        Throwable th1 = new Throwable("inner throwable th1");
        Throwable cause = th1.getCause();
        Exception ex1 = new Exception("inner exception ex1");
        cause = ex1.getCause();
        RuntimeException rtx1 = new RuntimeException("inner runtime exception r1x1");
        cause = rtx1.getCause();

        RuntimeException runtime1 = new RuntimeException(new RuntimeException(new Exception(rtx1)));

        //tasks.addAll(getAllCompleteWithTaskCauses(e));

        List<Throwable> finalList = getAllInnerCauses( runtime1 );
        */
    }

    private static List<Throwable> getAllInnerCauses(Throwable error) {
        List<Throwable> suppressed = new ArrayList<>();
        Throwable current = error;
        while (current != null) {
            suppressed.add(current);
            if(current.getCause() == null)
                System.out.println("lowest level cause " + current.getMessage() );
            current = current.getCause();
        }
        return suppressed;
    }
}
