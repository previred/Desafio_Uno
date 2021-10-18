package com.previred.desafiouno.service.impl;

import com.previred.desafiouno.dto.PeriodosDTO;
import com.previred.desafiouno.dto.PeriodosPerdidosDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;
import com.previred.desafiouno.util.RestClientUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PeriodoServiceImplTest {

	@InjectMocks
	private PeriodoServiceImpl periodoService;

	private MockedStatic<RestClientUtil> restClientUtilMock;

	String urlServicioGDD;
	PeriodosDTO periodosDTO;

	@BeforeEach
	void init() {
		urlServicioGDD = "http://localhost:8081/periodos/api";
		ReflectionTestUtils.setField(periodoService,"urlServicioGDD", urlServicioGDD);

		restClientUtilMock = Mockito.mockStatic(RestClientUtil.class);

		periodosDTO = new PeriodosDTO();
		periodosDTO.setFechas(Arrays.asList("2021-02-01","2021-04-01"));
		periodosDTO.setFechaCreacion("2021-01-01");
		periodosDTO.setFechaFin("2021-05-01");
		periodosDTO.setId(5);
	}

	@AfterEach
	void endTest() {
		restClientUtilMock.close();
	}

	@Test
	void getPeriodosFaltantesFromGDD_ok() throws FechaCreacionNulaException, FechaFinNulaException, RestClientException{

		restClientUtilMock.when(() -> RestClientUtil.getPeriodosFromGDD(urlServicioGDD)).thenReturn(periodosDTO);

		PeriodosPerdidosDTO respuesta = periodoService.getPeriodosFaltantesFromGDD();
		assertNotNull(respuesta);
	}

	@Test
	void getPeriodosFaltantesFromGDD_error_servicioGDD() {

		restClientUtilMock.when(() -> RestClientUtil.getPeriodosFromGDD(urlServicioGDD)).thenThrow(new RestClientException(""));

		Assertions.assertThrows(RestClientException.class, () -> {
			periodoService.getPeriodosFaltantesFromGDD();
		});
	}

	@Test
	void getPeriodosFaltantesFromGDD_fechaCreacion_nula() {

		periodosDTO.setFechaCreacion(null);
		restClientUtilMock.when(() -> RestClientUtil.getPeriodosFromGDD(urlServicioGDD)).thenReturn(periodosDTO);

		Assertions.assertThrows(FechaCreacionNulaException.class, () -> {
			periodoService.getPeriodosFaltantesFromGDD();
		});
	}

	@Test
	void getPeriodosFaltantesFromGDD_fechaFin_nula() {

		periodosDTO.setFechaFin(null);
		restClientUtilMock.when(() -> RestClientUtil.getPeriodosFromGDD(urlServicioGDD)).thenReturn(periodosDTO);

		Assertions.assertThrows(FechaFinNulaException.class, () -> {
			periodoService.getPeriodosFaltantesFromGDD();
		});
	}
}
