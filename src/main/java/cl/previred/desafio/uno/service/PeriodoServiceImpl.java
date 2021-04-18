package cl.previred.desafio.uno.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.previred.desafio.uno.client.PeriodoRestClient;
import cl.previred.desafio.uno.constants.DesafioUnoConstants;
import cl.previred.desafio.uno.dto.Periodo;
import cl.previred.desafio.uno.exception.PeriodoException;
import cl.previred.desafio.uno.vo.PeriodoFaltante;

@Service
public class PeriodoServiceImpl implements PeriodoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeriodoServiceImpl.class);
	
	@Autowired
	private PeriodoRestClient client;

	@Override
	public PeriodoFaltante getPeriodosFaltantes() {
		Periodo input = client.periodos().getBody();
		
		if(input == null || input.getFechas().isEmpty()) {
			LOG.error("El periodo es nulo o no tienes fechas");
			throw new PeriodoException("Los datos ingresados son nulos o estan vacios", 
					HttpStatus.NO_CONTENT, DesafioUnoConstants.ERROR_PROCESSING_DATES_CODE);
		}
		
		PeriodoFaltante periodoF = new PeriodoFaltante(input.getId(), input.getFechaCreacion(), input.getFechaFin(), input.getFechas(), null);
		
		try {
			LocalDate fechaInicio = input.getFechaCreacion();
			LocalDate fechaTermino = input.getFechaFin();
			long dif = ChronoUnit.MONTHS.between(fechaInicio, fechaTermino) + 1;
			
			// @formatter:off
			List<LocalDate> pf = Stream.iterate(fechaInicio, f -> f.plusMonths(1)).limit(dif)
					.filter(f -> input.getFechas().stream().noneMatch(fe -> fe.isEqual(f)))
					.collect(Collectors.toList());
			// @formatter:on
			
			LOG.debug("Analizando periodos faltantes entre el [{}] al [{}]", fechaInicio, fechaTermino);
			LOG.debug("Periodos totales [{}]", dif);			
			LOG.debug("Periodos Entregados [{}]", input.getFechas().size());
			LOG.debug("Periodos Faltantes [{}]", pf.size());
			
			periodoF.setFechasFaltantes(pf);
		} catch (Exception e) {
			LOG.error("Error al procesar los periodos", e);
		}
		
		return periodoF;
	}

}
