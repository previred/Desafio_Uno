package cl.cconcha.desafio1.data;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import cl.cconcha.desafio1.domain.Periodo;

public class PeriodoData {

	public static Periodo getPeriodos() {
		Periodo periodo = new Periodo();
		periodo.setId(0L);
		periodo.setFechaCreacion(LocalDate.of(1993, Month.SEPTEMBER, 1));
		periodo.setFechaFin(LocalDate.of(1995, Month.FEBRUARY, 1));
		
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(LocalDate.of(1993, Month.SEPTEMBER, 1));
		fechas.add(LocalDate.of(1993, Month.OCTOBER, 1));
		fechas.add(LocalDate.of(1993, Month.DECEMBER, 1));
		fechas.add(LocalDate.of(1994, Month.FEBRUARY, 1));
		fechas.add(LocalDate.of(1994, Month.MARCH, 1));
		fechas.add(LocalDate.of(1994, Month.JUNE, 1));
		fechas.add(LocalDate.of(1994, Month.AUGUST, 1));
		fechas.add(LocalDate.of(1994, Month.NOVEMBER, 1));
		fechas.add(LocalDate.of(1995, Month.JANUARY, 1));
		
		periodo.setFechas(fechas);
		return periodo;
	}
}
