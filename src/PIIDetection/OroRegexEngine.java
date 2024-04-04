package PIIDetection;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.oro.text.awk.*;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;


public class OroRegexEngine implements PiiDetectionEngine {
    private EnumSet<PiiField.Field> fields = EnumSet.allOf(PiiField.Field.class);
    private Set<String> customPatterns = new HashSet<>();
    //Perl5Util util = new Perl5Util();

    @Override
    public String description() {
        return "ORO regular expression library";
    }

    @Override
    public void add(String pattern) {

    }

    @Override
    public boolean hasPii(String content)  {
        boolean result = false;
        if(fields != null) {
            for (PiiField.Field field : fields) {
                try {
                    Pattern pattern = new AwkCompiler().compile(field.regex);
                    result = new AwkMatcher().matches(content, pattern);
                    if (result) return true;
                }
                catch (MalformedPatternException ex)
                {
                    return false;
                }
            }
        }

        for (String customPattern : customPatterns) {
            try {
                Pattern pattern = new AwkCompiler().compile(customPattern);
                result = new AwkMatcher().matches(content, pattern);
                if (result) return true;
            }
            catch (MalformedPatternException ex) {
                return false;
            }
        }
        return result;
    }
}
