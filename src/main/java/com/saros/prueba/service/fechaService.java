package com.saros.prueba.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class fechaService {

	
	public List<LocalDate> getFechasFaltantes (LocalDate fechaInicio, LocalDate fechaFin, 
			 List<LocalDate> lista)
	{
		Set<LocalDate> setFecha = new HashSet<>(lista);
		
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate> ();
		while (fechaInicio.isBefore(fechaFin))
		{    
			if (!setFecha.contains(fechaInicio))
				fechasFaltantes.add(fechaInicio);
			fechaInicio = fechaInicio.plusMonths(1);
		}
		return fechasFaltantes;
	}
	
}
