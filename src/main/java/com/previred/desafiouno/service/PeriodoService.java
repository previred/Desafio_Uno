package com.previred.desafiouno.service;

import com.previred.desafiouno.dto.PeriodosPerdidosDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;

public interface PeriodoService {
	PeriodosPerdidosDTO getPeriodosFaltantesFromGDD() throws FechaCreacionNulaException, FechaFinNulaException, RestClientException;
}
