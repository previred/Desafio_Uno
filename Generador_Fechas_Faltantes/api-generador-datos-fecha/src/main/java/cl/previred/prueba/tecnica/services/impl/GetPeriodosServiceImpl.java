package cl.previred.prueba.tecnica.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cl.previred.prueba.tecnica.entities.dto.PeriodosDTO;
import cl.previred.prueba.tecnica.providers.GetPeriodosProvider;
import cl.previred.prueba.tecnica.rest.response.PeriodoResponse;
import cl.previred.prueba.tecnica.services.GetPeriodosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jgajardo
 *
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetPeriodosServiceImpl implements GetPeriodosService {
	private static final int BORDE = 1;
	/**
	 * 
	 */
	private GetPeriodosProvider getPeriodosProvider;

	/**
	 * return ResponseEntity
	 */
	public ResponseEntity<PeriodoResponse> getPeriodos() {
		PeriodoResponse periodoResponse = null;
		try {
			PeriodosDTO periodo = getPeriodosProvider.getPeriods();
			log.info("Response servicio {} ", periodo.toString());

			long numOfMonths = ChronoUnit.MONTHS.between(periodo.getFechaCreacion(), periodo.getFechaFin()) + BORDE;
			List<LocalDate> listaTotalFechas = Stream.iterate(periodo.getFechaCreacion(), date -> date.plusMonths(1))
					.limit(numOfMonths).collect(Collectors.toList());
			List<LocalDate> diferencias = listaTotalFechas.stream().filter(d -> !periodo.getFechas().contains(d))
					.collect(Collectors.toList());
			log.info("Diferencias obtenidas {} ", diferencias.toString());
			periodoResponse = PeriodoResponse.builder().id(periodo.getId()).fechaCreacion(periodo.getFechaCreacion())
					.fechaFin(periodo.getFechaFin()).fechasFaltantes(diferencias).listaGenerada(periodo.getFechas())
					.build();
			log.info("Respuesta general {}", periodoResponse.toString());
		} catch (Exception e) {
			log.error("Error general en la implementacion ", e);
		}
		if (periodoResponse == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PeriodoResponse>(periodoResponse, HttpStatus.OK);
	}
}
