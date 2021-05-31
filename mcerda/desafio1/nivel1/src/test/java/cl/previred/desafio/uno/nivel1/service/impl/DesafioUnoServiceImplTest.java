package cl.previred.desafio.uno.nivel1.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.previred.desafio.uno.nivel1.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel1.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel1.service.OneLevelReaderFileService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest(properties = { "file.input=src/test/resources/basicTest.json", "file.output=docs/salida.json" })
class DesafioUnoServiceImplTest {
	@InjectMocks
	private DesafioUnoServiceImpl desafioUnoService;

	@Autowired
	private OneLevelReaderFileService oneLevelService;

	@BeforeEach
	public void init() {
		log.info("iniciando test");
		desafioUnoService = new DesafioUnoServiceImpl();
	}

	@Test
	void test() throws DesafioUnoInformedException {
		DesafioUnoFechasDTO fechasDTO = oneLevelService.readFile("src/test/resources/basicTest.json");
		log.info("********************{}**************", desafioUnoService);
		List<String> fechas = desafioUnoService.getFechasFaltantes(fechasDTO);
		assertNotNull(fechas);
	}

}
