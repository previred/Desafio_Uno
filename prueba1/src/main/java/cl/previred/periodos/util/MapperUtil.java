package cl.previred.periodos.util;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cl.previred.periodos.dto.PeriodoDTO;
import cl.previred.periodos.dto.PeriodosPerdidosResponseDTO;

@Component
public class MapperUtil {

	public static Logger logger = LoggerFactory.getLogger(MapperUtil.class);
	
	public static PeriodosPerdidosResponseDTO toPeriodosPerdidosResponseDTO(PeriodoDTO listaPeriodosRandom,
			List<LocalDate> listaPeriodosPerdidos) {
		PeriodosPerdidosResponseDTO periodosPerdidosResponseDTO
			= new PeriodosPerdidosResponseDTO();
		
		periodosPerdidosResponseDTO.setId(listaPeriodosRandom.getId());
		periodosPerdidosResponseDTO.setFechaCreacion(listaPeriodosRandom.getFechaCreacion());
		periodosPerdidosResponseDTO.setFechaFin(listaPeriodosRandom.getFechaFin());
		periodosPerdidosResponseDTO.setFechas(listaPeriodosRandom.getFechas());
		periodosPerdidosResponseDTO.setFechasFaltantes(listaPeriodosPerdidos);
		
		logger.debug("Archivo de respuesta: " + new Gson().toJson(periodosPerdidosResponseDTO));
		
		return periodosPerdidosResponseDTO;
	}

}
