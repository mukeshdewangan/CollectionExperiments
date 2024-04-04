package InstantPattern;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import static InstantPattern.FivetranDateTimeFormatters.*;

public class DriverClass {
    static DateTimeFormatter newFormatter = NEW_DEFAULT_INSTANT_FORMATTER ;//DateTimeFormatter.ISO_INSTANT;
    static DateTimeFormatter oldFormatter = DEFAULT_INSTANT_FORMATTER;


    public void localDateTimeFormaterTest(){
        //DateTimeFormatter formatter =
    }

    public Map<String,Instant> dateStrings(){
        Map<String, Instant> inputVsExpectedInstant = new HashMap<String, Instant>();
        inputVsExpectedInstant.put("2016-04-01T03:04:05Z", Instant.parse("2016-04-01T03:04:05Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1Z", Instant.parse("2016-04-01T03:04:05.100Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12Z", Instant.parse("2016-04-01T03:04:05.120Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123Z", Instant.parse("2016-04-01T03:04:05.123Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1234Z", Instant.parse("2016-04-01T03:04:05.1234Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12345Z", Instant.parse("2016-04-01T03:04:05.12345Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123456Z", Instant.parse("2016-04-01T03:04:05.123456Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1234567Z", Instant.parse("2016-04-01T03:04:05.1234567Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12345678Z", Instant.parse("2016-04-01T03:04:05.12345678Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123456789Z", Instant.parse("2016-04-01T03:04:05.123456789Z"));

        inputVsExpectedInstant.put("2016-04-01T3:4:5", Instant.parse("2016-04-01T03:04:05Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05", Instant.parse("2016-04-01T03:04:05Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1", Instant.parse("2016-04-01T03:04:05.001Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12", Instant.parse("2016-04-01T03:04:05.012Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123", Instant.parse("2016-04-01T03:04:05.123Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1234", Instant.parse("2016-04-01T03:04:05.001234Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12345", Instant.parse("2016-04-01T03:04:05.012345Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123456", Instant.parse("2016-04-01T03:04:05.123456Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.1234567", Instant.parse("2016-04-01T03:04:05.001234567Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.12345678", Instant.parse("2016-04-01T03:04:05.012345678Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05.123456789", Instant.parse("2016-04-01T03:04:05.123456789Z"));

        inputVsExpectedInstant.put("2016-04-01T3:4:5-3600", Instant.parse("2016-04-01T04:04:05Z"));
        inputVsExpectedInstant.put("2016-04-01T03:04:05-3600", Instant.parse("2016-04-01T04:04:05Z"));
        return inputVsExpectedInstant;
    }

    public static void main(String[] args) {
        //ECommInstantDeserializer();
        //KalpeshWHTest();
        for (int i = 0; i < 100; i++) {
            System.out.println(Instant.now());

        }
        /*String string = "2018-03-25T00:34:39.578";
        string = "2020-11-30T05:30:36.123456789 +10:00Z";
        TemporalAccessor t1 = CUSTOM_FORMATTER.parse(string);
        */

        //System.out.println(string + ": " + t1);
        //string = "2019-09-01 10:03:57.14-3600";
        //TemporalAccessor t = ISO_LOCAL_DATE_TIME.parse(string);
        //System.out.println(string + ": " + t);
        //t = newFormatter.parse(string);
        //System.out.println(string + ": " + t);
    }

    private static void ECommInstantDeserializer(){
        String eCommInstantStrings[] = {
                "2020-11-30T05:30:36.123456789 +01:00Z",
                "2020-11-30T05:30:36.123456789",
                "2020-11-30T05:30:36.123456",
                "2020-11-30T05:30:36.123",
                "2020-11-30 05:30:36.123"
                };

        for ( String s : eCommInstantStrings) {
            System.out.println(E_COMM_INSTANT_TIMESTAMP.parse(s));
        }
    }

    private static void KalpeshWHTest() {
        String str = "2021-03-31T19:13:36.85";
        //String str   = "2011-12-03T10:15:30Z";

        TemporalAccessor t = null, t1 = null;
        try {
             t = newFormatter.parse(str);
            System.out.println(t);
        }catch (DateTimeParseException e)
        {
            System.out.println(e);
        }

        try {
            t1 = oldFormatter.parse(str);
            System.out.println(t1);
        }catch (DateTimeParseException e)
        {
            System.out.println(e);
        }
        System.out.println(t1.equals(t));
    }
}
