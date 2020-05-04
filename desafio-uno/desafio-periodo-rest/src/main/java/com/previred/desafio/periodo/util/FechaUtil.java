package com.previred.desafio.periodo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 *
 * @author Roderick Rangel
 */
public class FechaUtil {
    
    public static Date dateStringToDate(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyy-MM-dd");
            return formatterFrom.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static int dateToNumber(Date date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyy-MM-dd");
            return Integer.parseInt(formatterFrom.format(date));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public static int dateStringFormatToNumber(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatterTo = new SimpleDateFormat("yyyyMMdd");
            return Integer.parseInt(formatterTo.format(formatterFrom.parse(date)));
        } catch (ParseException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public static String dateToStringFormat(Date date) {
        if (date != null) {
            SimpleDateFormat formatterTo = new SimpleDateFormat("yyyy-MM-dd");
            return formatterTo.format(date);
        } else {
            return "";
        }
    }
    
    public static long getDateDiffMonths(Date today, Date past) {
        return Months.monthsBetween(new DateTime(past), new DateTime(today)).getMonths();
    }
    
    public static String nextFirst(String fechaString)
    {
        Date date = FechaUtil.dateStringToDate(fechaString);
        Date firstDayOfNextMonth = (new DateTime(date).plusMonths(1).dayOfMonth().withMinimumValue()).toDate();
        
        return FechaUtil.dateToStringFormat(firstDayOfNextMonth);
    }
    
}
