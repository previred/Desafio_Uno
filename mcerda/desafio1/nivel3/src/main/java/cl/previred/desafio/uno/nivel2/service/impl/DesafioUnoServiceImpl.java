package cl.previred.desafio.uno.nivel2.service.impl;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.desafio.uno.nivel2.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel2.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel2.service.DesafioUnoService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DesafioUnoServiceImpl implements DesafioUnoService {


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
