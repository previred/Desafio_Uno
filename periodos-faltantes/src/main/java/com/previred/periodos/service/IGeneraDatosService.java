package com.previred.periodos.service;

import com.previred.periodos.model.Periodo;

public interface IGeneraDatosService {
	
	public Periodo obtenerGDD() throws Exception;
	
	public Periodo obtenerFechasFaltantes(Periodo payload) throws Exception;
	
	public Periodo obtenerGDDconFechasFaltantes() throws Exception;

}
