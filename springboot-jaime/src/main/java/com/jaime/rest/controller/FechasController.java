package com.jaime.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaime.rest.model.FechasJaime;
import com.jaime.rest.model.FechasServicio;
import com.jaime.rest.services.ServicioClientRest;
import com.jaime.rest.services.ServicioFechas;

@RestController
public class FechasController 
{
    @Autowired
    private ServicioFechas servicioFechas; 
    
    @Autowired
    private ServicioClientRest servicioClientRest;   

    @GetMapping(path="/fechasjaime", produces = "application/json")
    public FechasJaime obtenerFechas() { 

		try {
			
			FechasServicio fechasCiente = servicioClientRest.obtenerFechasServicio(); 
			
			List<String> fechasRestantes = servicioFechas.obtenerFechasRestantes(fechasCiente); 
			
			FechasJaime response = new FechasJaime(); 
			response.setFechasFaltantes(fechasRestantes); 
			response.setId(fechasCiente.getId()); 
			response.setFechaCreacion(fechasCiente.getFechaCreacion()); 
			response.setFechaFin(fechasCiente.getFechaFin()); 
			response.setFechas(fechasCiente.getFechas()); 
			
			return response; 

		} catch ( Exception ex ) { 
			System.err.println(ex.getMessage());
			throw new RuntimeException(ex.getMessage()); 
		} 

    }

}
