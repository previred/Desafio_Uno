package com.previred.periodos.tool;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de utilidades para crear periodos faltantes.
 * @author Mauricio
 *
 */
public final class PeriodoUtils {

	/**
	 * Crea lista de fechas correspondientes a un periodos de una fecha por cada
	 * mes, entre una fecha de inicio y otra de termino.
	 * 
	 * @param fechaInicio  Fecha de inicio del período.
	 * @param fechaTermino Fecha de termino del periodo.
	 * @return List
	 */
	public static List<LocalDate> crearPeriodosPerdidos(LocalDate fechaInicio, LocalDate fechaTermino, List<LocalDate> fechas) {

		List<LocalDate> periodos = new ArrayList<>();
		Period period = Period.between(fechaInicio, fechaTermino);
		int meses = 0;
		if (period.getYears() == 0) {
			meses = period.getMonths();
		} else {
			 meses = period.getYears() * period.getMonths();
		}
		LocalDate tmp = fechaInicio;
		while (periodos.size() < meses) {
			tmp = tmp.plusMonths(1);
			periodos.add(tmp);
		}
		return periodos.stream().filter(fecha -> !fechas.contains(fecha)).collect(Collectors.toList());
	}

}
