package com.springboot.periodos.perdidos.repository.imp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.springboot.periodos.perdidos.model.Periodos;
import com.springboot.periodos.perdidos.model.PeriodosFaltantes;
import com.springboot.periodos.perdidos.repository.CalcularPeriodosFaltantes;

/**
 * 
 * @author crist
 *
 */

@Service
public class CalcularPeriodosFaltantesImp implements CalcularPeriodosFaltantes {

	/**
	 * Se crea el objeto de respuesta
	 * luego de haber generado la lista de fechas faltantes en api GDD
	 */
	@Override
	public PeriodosFaltantes calcularPeriodos(Periodos periodos) {

		List<LocalDate> dates = getAllDays(periodos.getFechaCreacion(), periodos.getFechaFin());
		PeriodosFaltantes pf = new PeriodosFaltantes();
	
		List<LocalDate> nuevaLista = dates.stream().filter(d -> d.getDayOfMonth() == 1).collect(Collectors.toList());
		nuevaLista.removeAll(periodos.getFechas());
		
		pf.setId(periodos.getId());
		pf.setFechaCreacion(periodos.getFechaCreacion());
		pf.setFechaFin(periodos.getFechaFin());
		pf.setFechas(periodos.getFechas());
		pf.setFechasFaltantes(nuevaLista);
		
		return pf;
	}

	/**
	 * Obtenemos todas las fechas segun el rango
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<LocalDate> getAllDays(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}
}
