package com.desafioPrevired.consumingRest.service;

import java.time.LocalDate;
import java.util.List;

import com.desafioPrevired.consumingRest.model.PeriodoIn;
import com.desafioPrevired.consumingRest.model.PeriodoOut;

public interface RestService {
	
	public PeriodoIn obtenerPeriodo(String url); 
	
	public List<LocalDate> encontrarFechasFaltantes(LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas);
	
	public PeriodoOut obtenerPeriodosFaltantes(String url);
	
}
