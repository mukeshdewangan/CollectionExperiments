package PIIDetection;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class JavaPatternMatcher implements PiiDetectionEngine{
    private EnumSet<PiiField.Field> fields = EnumSet.allOf(PiiField.Field.class);
    private Set<String> customPatterns = new HashSet<>();

    /*
    RegexEngine(String customPattern){
        this.customPattern = customPattern;
    }

    RegexEngine(EnumSet<PiiField.Field> set){
        this.fields = set.clone();
    }
    */

    void addType(PiiField.Field patternType){
        //Collections.unmodifiableSet(set);
        fields.add(patternType);
    }

    void addTypes(EnumSet<PiiField.Field> patternTypes){
        fields.addAll(patternTypes);
    }

    @Override
    public void add(String pattern){
        customPatterns.add(pattern);
    }

    @Override
    public boolean hasPii(String input) {
        boolean result = false;
        if(fields != null) {
            for (PiiField.Field field : fields) {
                result = Pattern.compile(field.regex).matcher(input).matches();
                if (result) return true;
            }
        }

        for (String customPattern : customPatterns) {
            result = Pattern.compile(customPattern).matcher(input).matches();
            if (result) return true;
        }
        return result;
    }

    @Override
    public String description() {
        return "Regular Expression based PII detection using java.util.regex Pattern and Matcher";

    }
}
