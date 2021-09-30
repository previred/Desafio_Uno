package cl.pvr.fechas.faltantes.util;

import cl.pvr.fechas.faltantes.model.FechasFaltanteRespuesta;
import cl.pvr.fechas.faltantes.model.Periodo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProcesamientoFaltantes {
	
	private ProcesamientoFaltantes() {
		super();
	}
 
	//La clase FechasFaltanteRespuesta es llenada con la data obtenida previamente con el servicio GDD
	//Se procesa la fechaCreacion y fechaFin en el metodo ObtenerFechaFaltantes.
	 
	public static FechasFaltanteRespuesta obtenerFechasFaltantes(Periodo periodo) {
		FechasFaltanteRespuesta fechaF = new FechasFaltanteRespuesta();
		fechaF.setFechaCreacion(periodo.getFechaCreacion());
		fechaF.setFechaFin(periodo.getFechaFin());
		fechaF.setFechas(periodo.getFechas());
		fechaF.setId(periodo.getId());
		fechaF.setFechasFaltantes(procesarFechaFaltantes(periodo.getFechas(), periodo.getFechaCreacion(),periodo.getFechaFin()));
		
		return fechaF;
	}

	
	//Metodo que obtiene fechas faltantes.
	private static List<LocalDate> procesarFechaFaltantes(List<LocalDate> fechasEntrada, LocalDate inicio, LocalDate fin) {
		List<LocalDate> dates = Stream.iterate(inicio, date -> date.plusMonths(1))
			    .limit(ChronoUnit.MONTHS.between(inicio, fin))
			    .collect(Collectors.toList());
		   dates.removeAll(fechasEntrada);
		return dates;
	}

}

