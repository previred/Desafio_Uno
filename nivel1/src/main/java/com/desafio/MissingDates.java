package com.desafio;

import com.desafio.models.InputModel;
import com.desafio.models.OutputModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MissingDates {

    public static OutputModel find(InputModel input) throws ParseException {
        OutputModel output = new OutputModel();
        output.setId(input.getId());
        output.setFechaCreacion(input.getFechaCreacion());
        output.setFechaFin(input.getFechaFin());

        ArrayList<Date> outputDates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(input.getFechaCreacion());
        int initYear = calendar.get(Calendar.YEAR);
        int initMonth = calendar.get(Calendar.MONTH) + 1;

        calendar.setTime(input.getFechaFin());
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH) + 1;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = null;

        for (int year = initYear; year <= endYear; year++) {
            for (int month = initMonth; month <= 12; month++) {
                if (year == endYear && month > endMonth ) {
                    break;
                }
                tempDate = sdf.parse(year+"-"+month+"-01");
                if(!input.getFechas().contains(tempDate)){
                    outputDates.add(tempDate);
                }
            }
            initMonth = 1;
        }

        output.setFechasFaltantes(outputDates);
        return output;
    }
}
