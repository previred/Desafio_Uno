package cl.jose.huenul.periodos.perdidos.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cl.jose.huenul.periodos.perdidos.app.domain.PeriodosApiResponse;
import cl.jose.huenul.periodos.perdidos.app.dto.PeriodosPerdidosDTO;

@Component
public class PeriodosPerdidosUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeriodosPerdidosUtil.class);
	
	public List<String> obtenerPeriodosPerdidos(List<String> periodos) {
		LOG.info("Inicio - obtenerPeriodosPerdidos");
		List<String> periodosPerdidos = new ArrayList<>();
		for(int i = 0; i < periodos.size();i++) {
			LocalDate fechaInicio = LocalDate.parse(periodos.get(i), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			int calcularSiguienteIndice = i+1;
			if(calcularSiguienteIndice < periodos.size()) {
				LocalDate fechaFin = LocalDate.parse(periodos.get(i+1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalDate fechaPerdida = fechaInicio;
				for (LocalDate fechaI = fechaPerdida.plusMonths(1); fechaI.isBefore(fechaFin); fechaI = fechaI.plusMonths(1)) {
					periodosPerdidos.add(fechaI.toString());
				}
			}
		}
		LOG.info("Fin - obtenerPeriodosPerdidos");
		return periodosPerdidos;
	}

	public PeriodosPerdidosDTO fillPeriodosPerdidosDto(PeriodosApiResponse periodosResponse,
			List<String> periodosPerdidosList) {
		LOG.info("Inicio - fillPeriodosPerdidosDto");
		PeriodosPerdidosDTO periodosPerdidosDto = new PeriodosPerdidosDTO();
		periodosPerdidosDto.setId(periodosResponse.getId());
		periodosPerdidosDto.setFechaCreacion(periodosResponse.getFechaCreacion());
		periodosPerdidosDto.setFechaFin(periodosResponse.getFechaFin());
		periodosPerdidosDto.setFechas(periodosResponse.getFechas());
		periodosPerdidosDto.setFechasFaltantes(periodosPerdidosList);
		LOG.info("Fin - fillPeriodosPerdidosDto");
		return periodosPerdidosDto;
	}

}
