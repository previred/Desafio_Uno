package cl.julillo.datesrecovery.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import cl.julillo.datesrecovery.externalresources.feignclients.iGDDClient;
import cl.julillo.datesrecovery.model.dto.PeriodoDTO;
import cl.julillo.datesrecovery.service.iRecuperarFechasService;

/**
 * The Class RecuperarFechasServiceImpl.
 */
@Service
public class RecuperarFechasServiceImpl implements iRecuperarFechasService {

	/** The gdd cli. */
	@Autowired
	private iGDDClient gddCli;

	/**
	 * Recuperar fechas.
	 *
	 * @return the periodo DTO
	 */
	@Override
	public PeriodoDTO recuperarFechas() {
		PeriodoDTO dto = this.gddCli.getPeriodos(Collections.singletonMap(HttpHeaders.ACCEPT,
				MediaType.toString(Collections.singletonList(MediaType.APPLICATION_JSON))));
		dto.setFechasFaltantes(this.getfechasFaltantes(dto.getFechaCreacion(), dto.getFechaFin(), dto.getFechas()));
		return dto;
	}

	/**
	 * Gets the fechas faltantes.
	 *
	 * @param fechaCreacion the fecha creacion
	 * @param fechaFin the fecha fin
	 * @param fechas the fechas
	 * @return the fechas faltantes
	 */
	private List<LocalDate> getfechasFaltantes(LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		while (!fechaCreacion.isAfter(fechaFin)) {
			if (!fechas.contains(fechaCreacion)) {
				fechasFaltantes.add(fechaCreacion);
			}
			fechaCreacion = fechaCreacion.plusMonths(1L);
		}
		return fechasFaltantes;
	}

}
