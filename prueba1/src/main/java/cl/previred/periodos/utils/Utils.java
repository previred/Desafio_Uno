package cl.previred.periodos.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class Utils {

	public static List<LocalDate> generarTotalFechasInicioMes(@Valid LocalDate fechaCreacion,
			@Valid LocalDate fechaFin) {
		List<LocalDate> fechasInicioMes = new ArrayList<LocalDate>();
		LocalDate inicioMesMasCercano = buscaInicioMesMasCercano(fechaCreacion);
		LocalDate rangoFechaFin = obtienePrimerDiaMesActual(fechaFin).plusDays(1);
		
		for(LocalDate fechaActual = inicioMesMasCercano; fechaActual.isBefore(rangoFechaFin); fechaActual=fechaActual.plusMonths(1)) {
			fechasInicioMes.add(obtienePrimerDiaMesActual(fechaActual));
		}
		
		return fechasInicioMes;
	}

	private static LocalDate obtienePrimerDiaMesActual(LocalDate fechaActual) {
		return fechaActual.withDayOfMonth(1);
	}

	private static LocalDate buscaInicioMesMasCercano(@Valid LocalDate fechaCreacion) {
		if(fechaCreacion.getDayOfMonth() == 1)
			return fechaCreacion;
		return obtienePrimerDiaMesActual(fechaCreacion.plusMonths(1));
	}
	
	

}
