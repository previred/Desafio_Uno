package cl.pabloromero.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.pabloromero.gateway.DesafioUnoGDDGateway;
import cl.pabloromero.model.FechasFaltantesResponse;
import cl.pabloromero.model.Periodos;
import cl.pabloromero.service.DesafioUnoService;

@Service
public class DesafioUnoServiceImpl implements DesafioUnoService {

	@Autowired
	private DesafioUnoGDDGateway gddGateway;
	
	public FechasFaltantesResponse getFechasFaltantes() {
		Periodos periodos = gddGateway.periodos();
		List<LocalDate> fechasFaltantes =  calcularFechasFaltantes(periodos.getFechaCreacion(), periodos.getFechaFin(), periodos.getFechas());
		return FechasFaltantesResponse.builder()
				.id(periodos.getId())
				.fechaCreacion(periodos.getFechaCreacion())
				.fechaFin(periodos.getFechaFin())
				.fechas(periodos.getFechas())
				.fechasFaltantes(fechasFaltantes).build();
	}

	private List<LocalDate> calcularFechasFaltantes(LocalDate fechaCreacion, LocalDate fechaFin,
			List<LocalDate> fechas) {
		long diferenciaEntreFechas = ChronoUnit.MONTHS.between(YearMonth.from(fechaCreacion), YearMonth.from(fechaFin));

		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		fechasFaltantes.add(fechaCreacion);
		
		for (int i = 0; i < diferenciaEntreFechas; i++) {
			LocalDate fechaNueva = fechasFaltantes.get(i).plusMonths(1);
			fechasFaltantes.add(fechaNueva);
		}
		
		fechasFaltantes.removeAll(fechas);
		return fechasFaltantes;
	}
}
