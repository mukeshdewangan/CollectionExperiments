package PIIDetection;

import org.apache.regexp.RESyntaxException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) {
        String first = "first";
        String second = null;//"second";
        System.out.println(first.concat(second));
    }
    public static void main_(String[] args) throws RESyntaxException {

        List<PiiDetectionEngine> list = new ArrayList<>();
        list.add(new JavaPatternMatcher());
        //list.add(new GoogleRE2J());
        //list.add(new ApacheRegExp());
        //list.add(new OroRegexEngine());
        //for (int i = 0; i < 5; i++) {
            for (PiiDetectionEngine engine : list) {
                verifyMobileNumber(engine);
                verifySSN(engine);
                verifyPostalCode(engine);
                verifyEmailAddress(engine);
            }
        //}

    }

    private static void verifyEmailAddress(PiiDetectionEngine piiRe) throws RESyntaxException {
        // Verify Email address
        long start = System.nanoTime();
        System.out.println(piiRe.hasPii("+91-8087339090"));
        piiRe.hasPii("+91-8087339090");
        System.out.println(piiRe.hasPii("+"));
        piiRe.hasPii("+");
        System.out.println(piiRe.hasPii("mukesh.dewangan@fivetran.com"));
        piiRe.hasPii("mukesh.dewangan@fivetran.com");
        System.out.println(piiRe.hasPii("name@again@example.com"));
        piiRe.hasPii("name@again@example.com");
        System.out.println(piiRe.hasPii("anystring@anystring.anystring"));
        piiRe.hasPii("anystring@anystring.anystring");
        long end = System.nanoTime();
        System.out.println( (float) (end - start)/1000 + " in micro seconds for "+ piiRe.getClass());
    }
    private static void verifyPostalCode(PiiDetectionEngine piiRe) throws RESyntaxException {
        // Postal Code Pattern
        long start = System.nanoTime();
        System.out.println(piiRe.hasPii("+91-8087339090"));
        piiRe.hasPii("+91-8087339090");
        System.out.println(piiRe.hasPii("859-03-1702"));
        piiRe.hasPii("859-03-1702");
        System.out.println(piiRe.hasPii("59572"));
        piiRe.hasPii("59572");
        System.out.println(piiRe.hasPii("12345665"));
        piiRe.hasPii("12345665");
        long end = System.nanoTime();
        System.out.println( (float) (end - start)/1000 + " in micro seconds for "+ piiRe.getClass());
    }
    private static void verifySSN(PiiDetectionEngine piiRe) throws RESyntaxException {
        // SSN Pattern
        long start = System.nanoTime();
        System.out.println(piiRe.hasPii("+91-8087339090"));
        piiRe.hasPii("+91-8087339090");
        System.out.println(piiRe.hasPii("859-03-1702"));
        piiRe.hasPii("859-03-1702");
        System.out.println(piiRe.hasPii("000-45-6789"));
        piiRe.hasPii("000-45-6789");
        System.out.println(piiRe.hasPii("85-345-6789"));
        piiRe.hasPii("85-345-6789");
        System.out.println(piiRe.hasPii("856-45-67891"));
        piiRe.hasPii("856-45-67891");
        long end = System.nanoTime();
        System.out.println( (float) (end - start)/1000 + " in micro seconds for "+ piiRe.getClass());
    }
    private static void verifyMobileNumber(PiiDetectionEngine piiRe) throws RESyntaxException {
        // Mobile Number Pattern
        long start = System.nanoTime();
        System.out.println(piiRe.hasPii("+91-8087339090"));
        piiRe.hasPii("+91-8087339090");
        System.out.println(piiRe.hasPii("8087339090"));
        piiRe.hasPii("8087339090");
        System.out.println(piiRe.hasPii("+1-8087339090"));
        piiRe.hasPii("+1-8087339090");
        System.out.println(piiRe.hasPii("+91 8087339090"));
        piiRe.hasPii("+91 8087339090");
        System.out.println(piiRe.hasPii("+912 8087339090"));
        piiRe.hasPii("+912 8087339090");
        System.out.println(piiRe.hasPii("+91-846363"));
        piiRe.hasPii("+91-846363");
        System.out.println(piiRe.hasPii("0000000000"));
        piiRe.hasPii("0000000000");
        long end = System.nanoTime();
        System.out.println( (float) (end - start)/1000 + " in micro seconds for "+ piiRe.getClass());
    }
}
