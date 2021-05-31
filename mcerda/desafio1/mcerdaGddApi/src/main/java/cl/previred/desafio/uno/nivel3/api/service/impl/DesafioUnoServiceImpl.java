package cl.previred.desafio.uno.nivel3.api.service.impl;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.desafio.uno.nivel3.api.config.DesafioUnoConstants;
import cl.previred.desafio.uno.nivel3.api.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel3.api.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel3.api.service.DesafioUnoService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DesafioUnoServiceImpl implements DesafioUnoService {

	@Override
	public List<String> getFechasFaltantes(DesafioUnoFechasDTO fechas) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DesafioUnoConstants.GENERAL_DATE_FORMAT);
		List<LocalDate> listFechasCargadas = fechas.getFechas().parallelStream().map(f -> LocalDate.parse(f, formatter))
				.collect(Collectors.toList());
		List<LocalDate> listTodasFechas = new ArrayList<>();
		LocalDate startDate = fechas.getFechaCreacion();
		LocalDate endDate = fechas.getFechaFin();
		while (startDate.isBefore(endDate)) {
			listTodasFechas.add(startDate);
			startDate = startDate.plusMonths(1);
		}
		List<LocalDate> result = listTodasFechas.parallelStream().filter(e -> !listFechasCargadas.contains(e))
				.collect(Collectors.toList());
		return result.parallelStream().map(s -> s.format(formatter)).collect(Collectors.toList());
	}

	@Override
	public void print(DesafioUnoFechasDTO fechas) throws DesafioUnoInformedException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fechas);
			log.info("Resultado:\n {}", data);
			String pathFile = "docs/out.json";
			log.info("Guardando resultado en archivo: {}", pathFile);
			mapper.writeValue(Paths.get(pathFile).toFile(), fechas);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new DesafioUnoInformedException();
		}

	}

}
