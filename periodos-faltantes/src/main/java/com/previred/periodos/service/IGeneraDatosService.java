package com.previred.periodos.service;

import com.previred.periodos.model.Periodo;

public interface IGeneraDatosService {
	
	public Periodo obtenerGDD();
	
	public Periodo obtenerFechasFaltantes(Periodo payload);
	
	public Periodo obtenerGDDconFechasFaltantes();

}
