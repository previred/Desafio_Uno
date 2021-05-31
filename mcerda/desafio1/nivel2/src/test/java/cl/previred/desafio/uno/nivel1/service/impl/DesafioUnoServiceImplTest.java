package cl.previred.desafio.uno.nivel1.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.desafio.uno.nivel2.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel2.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel2.service.OneLevelCallGDDService;
import cl.previred.desafio.uno.nivel2.service.impl.DesafioUnoServiceImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@SpringBootApplication
class DesafioUnoServiceImplTest {
	@InjectMocks
	private DesafioUnoServiceImpl desafioUnoService;

	@Mock
	private OneLevelCallGDDService oneLevelService;

	@BeforeEach
	public void init() throws DesafioUnoInformedException {
		log.info("iniciando test");
		desafioUnoService = new DesafioUnoServiceImpl();
		ObjectMapper mapper = new ObjectMapper();
		DesafioUnoFechasDTO result = DesafioUnoFechasDTO.builder().build();
		try {
			result = mapper.readValue(Paths.get("src/test/resources/basicTest.json").toFile(),
					DesafioUnoFechasDTO.class);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		when(oneLevelService.callGetGDD()).thenReturn(result);
	}

	@Test
	void test() throws DesafioUnoInformedException {
		DesafioUnoFechasDTO fechasDTO = oneLevelService.callGetGDD();
		log.info("********************{}**************", fechasDTO);
		List<String> fechas = desafioUnoService.getFechasFaltantes(fechasDTO);
		assertNotNull(fechas);
	}

}
