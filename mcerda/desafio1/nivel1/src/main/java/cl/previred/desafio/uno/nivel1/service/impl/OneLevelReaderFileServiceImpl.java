package cl.previred.desafio.uno.nivel1.service.impl;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.desafio.uno.nivel1.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel1.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel1.service.OneLevelReaderFileService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OneLevelReaderFileServiceImpl implements OneLevelReaderFileService {
	@Value("app.env")
	private String env;

	@Override
	public DesafioUnoFechasDTO readFile(String pathFile) throws DesafioUnoInformedException {
		log.info("leyendo archivo: {}", pathFile);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(Paths.get(pathFile).toFile(), DesafioUnoFechasDTO.class);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		throw new DesafioUnoInformedException();
	}

}
