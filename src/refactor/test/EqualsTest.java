package refactor.test;

import java.util.ArrayList;
import java.util.List;

public class EqualsTest {
    private static boolean hasMore;
    public static List<String> list = new ArrayList<>();
    public static boolean hasNext(){
        if (list.isEmpty())
            return hasMore = false;
        else
            hasMore = true ;
        return hasMore;
    }

    public static void main(String[] args) {
        //System.out.println( EqualsTest.hasNext());

        List<String> list = new ArrayList<>();
        list.add("first"); list.add("second"); list.add("third"); list.add("fourth");
        for ( String item: list) {
            System.out.println( item);
            System.out.println("returing");
        }
    }
}
