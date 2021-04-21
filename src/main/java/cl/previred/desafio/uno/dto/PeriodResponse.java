package cl.previred.desafio.uno.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PeriodResponse extends GDDApiPeriodResponse{
	
	private List<LocalDate> fechasFaltantes;
	
	public PeriodResponse(GDDApiPeriodResponse apiResponse) {
		Set<LocalDate> dates = new HashSet<LocalDate>(apiResponse.getFechas());
		List<LocalDate> missingDates = apiResponse.getFechaCreacion()
				.datesUntil(apiResponse.getFechaFin().plusDays(1), Period.ofMonths(1))
				.filter(d -> !dates.contains(d))
				.collect(Collectors.toList());
		
		this.setId(apiResponse.getId());
		this.setFechaCreacion(apiResponse.getFechaCreacion());
		this.setFechaFin(apiResponse.getFechaFin());
		this.setFechas(apiResponse.getFechas());
		fechasFaltantes = missingDates;
	}

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	
}
