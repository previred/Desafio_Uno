package cl.previred.desafioUno.servicio;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.previred.desafioUno.dto.PeriodoDTO;

@Service
public class PeriodoServicio {
	@Value("${urlApi}")
	String urlApi;

	public PeriodoDTO get() {

		RestTemplate restTemplate = new RestTemplate();
		PeriodoDTO periodoDTO = restTemplate.getForObject(this.urlApi, PeriodoDTO.class);
		return periodoDTO;
	}

	public void completar(PeriodoDTO periodoDTO) {
		periodoDTO.setFechasFaltantes(new ArrayList<LocalDate>());
		LocalDate fecha = periodoDTO.getFechaCreacion();
		while (fecha.isBefore(periodoDTO.getFechaFin())) {
			if (!periodoDTO.getFechas().contains(fecha)) {
				periodoDTO.getFechasFaltantes().add(fecha);
			}
			fecha = fecha.plusMonths(1);
		}

	}
}
