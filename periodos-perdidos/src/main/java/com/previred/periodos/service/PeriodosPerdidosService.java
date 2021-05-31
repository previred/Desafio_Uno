package com.previred.periodos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.periodos.exception.PeriodosServiceException;
import com.previred.periodos.model.PeriodoResponse;
import com.previred.periodos.tool.PeriodoUtils;

@Service
public class PeriodosPerdidosService {
	
	@Autowired
	private ApiPeriodosClient periodosClient;

	public PeriodoResponse buscarPeriodos() throws PeriodosServiceException {
		PeriodoResponse response = periodosClient.buscarPeriodos();
		List<LocalDate> fechasFaltantes = PeriodoUtils.crearPeriodosPerdidos(response.getFechaCreacion(), response.getFechaFin(), response.getFechas());
		response.setFechasFaltantes(fechasFaltantes);
		return response;
	}

}
