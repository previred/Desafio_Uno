package com.devru.desafiouno.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DesafioUtils {
	
	public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static synchronized Date santiagoDate() {
        
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        Date date = cal.getTime();
            
        int zone = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (calendar.get(Calendar.MONTH) + 1 > 1) {
            if (calendar.get(Calendar.MONTH) + 1 == 4) {
                if (calendar.get(Calendar.DAY_OF_MONTH) > 6) {
                    zone = -4;
                } else {
                    zone = -3;
                }

            } else if (calendar.get(Calendar.MONTH) + 1 == 9) {
                if (calendar.get(Calendar.DAY_OF_MONTH) > 7) {
                    zone = -3;
                } else {
                    zone = -4;
                }
            } else if (calendar.get(Calendar.MONTH) + 1 > 4 && calendar.get(Calendar.MONTH) + 1 < 9) {
                zone = -4;
            } else {
                zone = -3;
            }

        } else if (calendar.get(Calendar.MONTH) + 1 == 1) {
            zone = -3;
        }
        calendar.add(Calendar.HOUR, zone);
        return calendar.getTime();
    } 
    
    public static synchronized String formatDate(Date date, String dateFormat){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }    
    
}
