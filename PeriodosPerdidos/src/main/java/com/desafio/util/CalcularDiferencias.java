package com.desafio.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalcularDiferencias {
	public List<LocalDate> ListaEntreFechas(LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		//default time zone
    	ZoneId defaultZoneId = ZoneId.systemDefault();
    	LocalDate localDateInicio = LocalDate.of(fechaCreacion.getYear(), fechaCreacion.getMonthValue(), 1);
    	LocalDate localDateTermino = LocalDate.of(fechaFin.getYear(), fechaFin.getMonthValue(), 1);
    	
    	Date fechaInicio = Date.from(localDateInicio.atStartOfDay(defaultZoneId).toInstant());
    	Date fechaTermino = Date.from(localDateTermino.atStartOfDay(defaultZoneId).toInstant());
    	
		Calendar c1 = Calendar.getInstance();
	    c1.setTime(fechaInicio);
	   
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(fechaTermino);
	    List<LocalDate> listaFechas = new ArrayList<LocalDate>();
	    while (!c1.after(c2)) {	    
	    	LocalDate fechaTemp = convertToLocalDateViaSqlDate(c1.getTime());
	    	if(!fechas.contains(fechaTemp))
	    	{
	    		listaFechas.add(fechaTemp);
	    	}	        
	        c1.add(Calendar.MONTH, 1);
	    }
	    return listaFechas;
	}
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
