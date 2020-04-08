package com.luisnunez.springboot.api.periodos.faltantes.models.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.luisnunez.springboot.api.periodos.faltantes.models.Periodos;
import com.luisnunez.springboot.api.periodos.faltantes.models.PeriodosFaltantes;

import brave.Tracer;

@Service
public class PeriodoFaltanteImpl implements PeriodoFaltanteService {

	private Logger log = LoggerFactory.getLogger(PeriodoFaltanteImpl.class);

	private Tracer tracer;

	@Autowired
	private RestTemplate clientRest;

	/**
	 * Metodo que entrega los periodos faltantes entregados por el API GDD
	 */
	@Override
	public PeriodosFaltantes traePeriodosFaltantes() {
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		PeriodosFaltantes periodosFaltantes = new PeriodosFaltantes();

		try {
			Periodos periodoResponse = clientRest.getForObject("http://localhost:8080/periodos/api", Periodos.class);

			periodosFaltantes.setPeriodos(periodoResponse);

			long monthBetween = ChronoUnit.MONTHS.between(periodoResponse.getFechaCreacion(),
					periodoResponse.getFechaFin());

			for (int i = 0; i < monthBetween; i++) {
				fechas.add(periodoResponse.getFechaCreacion().plusMonths(i).withDayOfMonth(1));
			}

			for (int i = 0; i <= periodoResponse.getFechas().size() - 1; i++) {
				for (int j = 0; j <= fechas.size() - 1; j++) {
					if (fechas.get(j).equals(periodoResponse.getFechas().get(i))) {
						fechas.remove(j);
					}
				}
			}

			periodosFaltantes.setFechasFaltantes(fechas);

			return periodosFaltantes;

		} catch (HttpClientErrorException e) {
			String error = "A ocurrido un error al intentar ejecutar el servicio GDD";
			log.error(error);

			// tracer sirve para enviar traza a Zinkin Server en caso de que este activo
			tracer.currentSpan().tag("error.mensaje", error + ": " + e.getMessage());

			throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, error);
		}
	}

	/**
	 * Metodo que entrega los periodos generados desde la API de GDD
	 */
	@Override
	public Periodos traePeriodosGDD() {
		try {
			Periodos periodoResponse = clientRest.getForObject("http://localhost:8080/periodos/api", Periodos.class);
			return periodoResponse;
		} catch (HttpClientErrorException e) {
			String error = "A ocurrido un error al intentar ejecutar el servicio GDD";
			log.error(error);

			// tracer sirve para enviar traza a Zinkin Server en caso de que este activo
			tracer.currentSpan().tag("error.mensaje", error + ": " + e.getMessage());

			throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, error);
		}

	}

}
