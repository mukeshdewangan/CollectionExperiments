package PIIDetection;


import org.apache.regexp.RESyntaxException;

public interface PiiDetectionEngine {
    String description();
    void add(String pattern);
    boolean hasPii(String content) throws RESyntaxException;
}
