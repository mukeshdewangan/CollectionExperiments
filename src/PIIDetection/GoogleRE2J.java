package PIIDetection;

import com.google.re2j.Pattern;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GoogleRE2J implements PiiDetectionEngine{
    private EnumSet<PiiField.Field> fields = EnumSet.allOf(PiiField.Field.class);
    private Set<String> customPatterns = new HashSet<>();

    @Override
    public boolean hasPii(String input) {
        boolean result = false;
        if(fields != null) {
            for (PiiField.Field field : fields) {
                result = Pattern.compile(field.regex).matches(input);
                if (result) return true;
            }
        }

        for (String customPattern : customPatterns) {
            result = Pattern.compile(customPattern).matches(input);
            if (result) return true;
        }
        return result;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public void add(String pattern) {

    }


}
