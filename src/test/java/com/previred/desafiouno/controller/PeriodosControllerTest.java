package com.previred.desafiouno.controller;

import com.previred.desafiouno.dto.BaseResponseDTO;
import com.previred.desafiouno.dto.PeriodosDTO;
import com.previred.desafiouno.dto.PeriodosPerdidosDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;
import com.previred.desafiouno.service.PeriodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PeriodosControllerTest {

	@InjectMocks
	private PeriodosController periodosController;

	@Mock
	private PeriodoService periodoService;

	@Test
	void obtienePeriodosFaltantes_ok() throws FechaCreacionNulaException, FechaFinNulaException, RestClientException {
		when(periodoService.getPeriodosFaltantesFromGDD()).thenReturn(new PeriodosPerdidosDTO(new PeriodosDTO(), null));

		BaseResponseDTO<PeriodosPerdidosDTO> respuesta = periodosController.obtienePeriodosFaltantes();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getResultado());
	}
}
