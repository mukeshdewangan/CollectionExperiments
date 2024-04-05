package InstantPattern;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

import static InstantPattern.FivetranDateTimeFormatters.DEFAULT_INSTANT_FORMATTER;
import static InstantPattern.FivetranDateTimeFormatters.NEW_DEFAULT_INSTANT_FORMATTER;

public class FormatterOperations {
    public static final boolean IS_JAVA_8 =
            System.getProperty("java.version") != null && System.getProperty("java.version").startsWith("1.8.");

    private final DateTimeFormatter instantPattern;
    boolean checkNewDefaultInstantPattern = true;

    public FormatterOperations(DateTimeFormatter instantPattern) {
        this.instantPattern = instantPattern;
    }

    private static DateTimeFormatter correctInstantPattern(DateTimeFormatter pattern) {
        if (pattern == DEFAULT_INSTANT_FORMATTER && (!IS_JAVA_8)) {
            return NEW_DEFAULT_INSTANT_FORMATTER;
        }
        return pattern;
    }

    private Instant parseInstant(String value, DateTimeFormatter formatter) {
        try {
            return queryInstant(formatter.parse(value));
        } catch (DateTimeParseException e) {
            TemporalAccessor parsed = tryParsingOutOfRangeDateValues(value, instantPattern, e);
            return queryInstant(parsed);
        }
    }

    private Instant queryInstant(TemporalAccessor parsed) {
        if (parsed.isSupported(ChronoField.INSTANT_SECONDS)) {
            return parsed.query(Instant::from);
        }
        if (parsed.isSupported(ChronoField.MILLI_OF_SECOND)) {
            return parsed.query(LocalDateTime::from).toInstant(ZoneOffset.UTC);
        }
        return parsed.query(LocalDate::from).atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    /**
     * ISO formatter is able to parse timestamps like +12345-01-01 but fails with 12345-01-01 This is a try to parse by
     * adding + for invalid values. Exception will be thrown if parsing fails after adding +
     */
    private TemporalAccessor tryParsingOutOfRangeDateValues(
            String value, DateTimeFormatter formatter, DateTimeParseException originalException) {
        try {
            System.out.println("Trying to parse " + value + " to date format");
            value = "+" + value;
            return formatter.parse(value);
        } catch (DateTimeParseException e) {
            throw originalException;
        }
    }

    private Instant parseInstantWithCheck(String value) {
        Instant result = parseInstant(value, instantPattern);
        if (checkNewDefaultInstantPattern) {
            Instant oldResult = parseInstant(value, DEFAULT_INSTANT_FORMATTER);
            if (!Objects.equals(result, oldResult)) {
                throw new IllegalStateException(
                        String.format(
                                "Got different result with new default instant pattern, value: %s, result: %s, old: %s",
                                value, result, oldResult));
            }
        }
        return result;
    }
}
