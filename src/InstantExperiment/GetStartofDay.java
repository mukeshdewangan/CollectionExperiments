package InstantExperiment;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetStartofDay {
    public static void main(String[] args) {
        Instant instant = Instant.parse("2022-07-07T11:27:35.905Z");
        System.out.println(startOfTheDay(instant));
    }


    public static Calendar convertInstantToCalendar(Instant instant) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return GregorianCalendar.from(zdt);
    }


    public static Instant startOfTheDay(Instant instant) {
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
