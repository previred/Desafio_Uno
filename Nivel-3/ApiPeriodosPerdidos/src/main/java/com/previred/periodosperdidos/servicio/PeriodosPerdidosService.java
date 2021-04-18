package com.previred.periodosperdidos.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.periodosperdidos.client.IPeriodosClient;
import com.previred.periodosperdidos.swagger.codegen.model.Periodo;

@Service
public class PeriodosPerdidosService {
	
	@Autowired
	private IPeriodosClient client;
	
	public Periodo obtenerPeriodosPerdidos() {
		return client.periodos();
	}
	
	
	

}
