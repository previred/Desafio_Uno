package com.desafio.desafioCayo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    private static final String YYYYMMDD = "yyyy-MM-dd";

    public static Date convertStringToDate_YYYYMMDD(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
        Date date = null;
        try {

            date = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString_YYYYMMDD(Date fecha) {
        String strDate = null;
        if (fecha != null) {
            DateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);

            strDate = dateFormat.format(fecha);
        }

        return strDate;
    }

    public static Date sumarRestarDiasFecha(String fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate_YYYYMMDD(fecha)); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, meses);  // numero de meses

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos meses añadidos

    }
}
