package cl.previred.challenge.periods.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.y.z - 22-10-2019
 * @since 1.y.z - 22-10-2019
 */
public class CalendarParser {

    private static String DEFAULT_PATTERN = "yyy-MM-dd";

    public static CalendarParser build() {
        return new CalendarParser();
    }

    public Calendar parse(String date) throws ParseException {
        return parse(date, DEFAULT_PATTERN);
    }

    public Calendar parse(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(date));
        return cal;
    }

}
