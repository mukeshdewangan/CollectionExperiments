import java.util.Map;
import java.util.Optional;

public class JavaOptionalExperiment {

    static boolean checkOptional(Optional<Boolean> excludedByUser){
        // return ( excludedByUser.equals(Optional.of(true)));
        return excludedByUser.orElse(false);
        //System.out.println();
    }
    public static void main(String[] args) {

        Optional<Boolean> defaultVal = Optional.empty();
        Optional<Boolean> trueVal = Optional.of(true);
        Optional<Boolean> falseVal = Optional.of(false);

        System.out.println(checkOptional(defaultVal));
        System.out.println(checkOptional(trueVal));
        System.out.println(checkOptional(falseVal));



    }
}
