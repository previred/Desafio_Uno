package com.previred.desafioUno.service;

import com.previred.desafioUno.exception.DesafioException;
import com.previred.desafioUno.rest.client.domain.PeriodosResponse;

public interface DesafioService {

	PeriodosResponse executeRandomDateGenerator() throws DesafioException;

}
