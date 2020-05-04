package com.app.dev;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dto.Periodo;

public class FechaUtils {
	private static FechaUtils instance = null;

	protected FechaUtils() {
    }
 
    public static FechaUtils getInstance() {
        if (instance == null) {
            instance = new FechaUtils();
        }
 
        return instance;
    }
	
	public static List<Date> getFechasFromPeriodo(Periodo periodo) {
		LocalDate fechaInicio = periodo.getFechaCreacion();
		LocalDate fechaFin = periodo.getFechaFin();
		List<Date> fechasFaltantes = new ArrayList<Date>();
		List<Date> fechas = periodo.getFechas();
		List<LocalDate> fechasDt = new ArrayList<LocalDate>();
		for (Date date : fechas) {
			fechasDt.add(date.toInstant().atZone(ZoneId.of("Z")).toLocalDate());
		}
		fechaInicio = fechaInicio.plusMonths(1);
		while(fechaInicio.isBefore(fechaFin)) {
//			System.out.println("FECHA INICIO DATETIME: " +  fechaInicio);
//			System.out.println("FECHA INICIO DATE    : " + Date.from(fechaInicio.atStartOfDay()
//				      .atZone(ZoneId.systemDefault())
//				      .toInstant()));
			if(!fechasDt.contains(fechaInicio)) {
				fechasFaltantes.add(Date.from(fechaInicio.atStartOfDay()
					      .atZone(ZoneId.systemDefault())
					      .toInstant()));
			}
			fechaInicio = fechaInicio.plusMonths(1);
			
		}
		//System.out.println(fechasFaltantes.toString());
		
		return fechasFaltantes;
	}
}
