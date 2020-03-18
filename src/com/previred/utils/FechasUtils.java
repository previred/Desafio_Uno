package com.previred.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FechasUtils {

	/**
	 * Convierte una fecha en formato Date a LocaleDate
	 * @param fecha
	 * @return
	 */
	public LocalDate toLocalDate(Date fecha) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instantCreacion = fecha.toInstant();
		LocalDate localDate = instantCreacion.atZone(defaultZoneId).toLocalDate();
		
		return localDate;
	}
	
	/**
	 * Convierte una fecha en String a LocaDate
	 * @param dateStr
	 * @return
	 */
	public LocalDate toLocalDate(String dateStr) {
		LocalDate localDate = LocalDate.parse(dateStr);
		
        return localDate;
	}
	
	/**
	 * Obtiene las fechas, por periodo mensual, dado un rango determinado
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public List<LocalDate> getFechasListaCompleta(LocalDate fechaInicio, LocalDate fechaFin){ 
		List<LocalDate> fechasListaCompleta = new ArrayList<LocalDate>();
	
		while(fechaInicio.isBefore(fechaFin)) {
			fechaInicio = fechaInicio.plusMonths(1);
			fechasListaCompleta.add(fechaInicio);
		}
		
		return fechasListaCompleta;
	}
	
	/**
	 * Convierte un listado de fechas en formato Date a una lista en formato LocalDate
	 * @param fechas
	 * @return
	 */
	public List<LocalDate> getLocalDatesFromDates(List<Date> fechas){
		List<LocalDate> fechasEntradaLD = new ArrayList<LocalDate>();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		for (Date date : fechas) {	
			Instant instantCreacion = date.toInstant();
			LocalDate localDate = instantCreacion.atZone(defaultZoneId).toLocalDate();
			
			fechasEntradaLD.add(localDate);
		}
		
		return fechasEntradaLD;
	}
	
	/**
	 * Convierte un listado de fechas en formato String a una lista en formato LocalDate
	 * @param fechas
	 * @return
	 */
	public List<LocalDate> getLocalDatesFromDatesStr(List<String> fechas){
		List<LocalDate> fechasEntradaLD = new ArrayList<LocalDate>();
		
		for (String dateStr : fechas) {	
			LocalDate localDate = LocalDate.parse(dateStr);
			
			fechasEntradaLD.add(localDate);
		}
		
		return fechasEntradaLD;
	}
	
	
	public List<String> getStringDatesFromLocalDates(List<LocalDate> fechas){
		List<String> fechasStr = new ArrayList<String>();
		
		for (LocalDate date : fechas) {				
			fechasStr.add(date.toString());
		}
		
		return fechasStr;
	}
	
	
	/**
	 * Descuenta de un listado de fechas las fechas repetidas
	 * @param listaCompleta
	 * @param listaToRemove
	 * @return
	 */
	public List<LocalDate> getFechasFaltantes(List<LocalDate> listaCompleta, List<LocalDate> listaToRemove){
		
		for (LocalDate fechaEntrada : listaToRemove) {
			for (LocalDate fecha : listaCompleta) {
				if(fechaEntrada.toString().equals(fecha.toString())) {
					listaCompleta.remove(fecha);
					break;
				}
			}
		}
		
		return listaCompleta;
	}
	
	
}
