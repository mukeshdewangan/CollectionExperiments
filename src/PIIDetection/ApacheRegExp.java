package PIIDetection;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ApacheRegExp implements PiiDetectionEngine {
    private EnumSet<PiiField.Field> fields = EnumSet.allOf(PiiField.Field.class);
    private Set<String> customPatterns = new HashSet<>();

    @Override
    public boolean hasPii(String input) throws RESyntaxException {
        boolean result = false;
        if(fields != null) {
            for (PiiField.Field field : fields) {
                result = new RE(field.regex).match(input);
                if (result) return true;
            }
        }

        for (String customPattern : customPatterns) {
            result = new RE(customPattern).match(input);
            if (result) return true;
        }
        return result;
    }

    @Override
    public String description() {
        return "Apache JRegex";
    }

    @Override
    public void add(String pattern) {
        customPatterns.add(pattern);
    }

    void addType(PiiField.Field patternType){
        //Collections.unmodifiableSet(set);
        fields.add(patternType);
    }

    void addTypes(EnumSet<PiiField.Field> patternTypes){
        fields.addAll(patternTypes);
    }


}
