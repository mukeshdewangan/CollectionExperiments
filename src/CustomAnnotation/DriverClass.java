package CustomAnnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverClass {
    public static void main(String[] args) {
        Train newTrain = new Train("havingTrain", 4);


        //MyAnnotation m = newTrain.getClass().getAnnotation(MyAnnotation.class);
        //Train.class.getAnnotation(MyAnnotation.class).
        //System.out.println(" Annotation value1 "+ m.value()  + " value2 "+ m.value2() );

        //StaticSchemaTable.class.getAnnotation(PIIAnnotation.class);

        String pattern = "[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,5}";
        String input = "mukesh.d1_23-ew@fivet1ran.com";
        //Pattern p = Pattern(pattern).matcher(input);

        Matcher matcher = Pattern.compile(pattern).matcher(input);

        List<String> listMatches = new ArrayList<String>();

        while(matcher.find())
        {
            listMatches.add(matcher.group(0));
        }

        for(String s : listMatches)
        {
            System.out.println(s);
        }

        boolean result = matcher.matches();
        System.out.println( result);


    }
}
