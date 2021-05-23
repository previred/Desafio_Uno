package cl.irk.periodos.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cl.irk.periodos.model.Periodo;
import cl.irk.periodos.model.PeriodoFull;

public class GenerateOutput {
	
	private GenerateOutput() {
		super();
	}
 
	public static PeriodoFull obtieneFaltantes(Periodo per) {
		PeriodoFull pf = new PeriodoFull();
		pf.setFechaCreacion(per.getFechaCreacion());
		pf.setFechaFin(per.getFechaFin());
		pf.setFechas(per.getFechas());
		pf.setFechasFaltantes(calculaFaltanter(per.getFechas(), per.getFechaCreacion(),per.getFechaFin()));
		pf.setId(per.getId());
		
		return pf;
	}

	private static List<LocalDate> calculaFaltanter(List<LocalDate> fechasEntrada, LocalDate inicio, LocalDate fin) {
		List<LocalDate> dates = Stream.iterate(inicio, date -> date.plusMonths(1))
			    .limit(ChronoUnit.MONTHS.between(inicio, fin))
			    .collect(Collectors.toList());
		dates.removeAll(fechasEntrada);
		return dates;
	}

}
