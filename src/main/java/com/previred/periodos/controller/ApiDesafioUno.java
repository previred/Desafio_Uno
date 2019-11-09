package com.previred.periodos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.periodos.beans.PeriodoResponse;
import com.previred.periodos.service.ServicioDesafioUno;

@RestController
public class ApiDesafioUno {
	
	@Autowired
	private ServicioDesafioUno servicioDesafioUno;
	
	
	/*@RequestMapping(value="/calculoFechas", method=RequestMethod.GET)
	public String prueba(@RequestParam(value = "nombre") String nombre ) {
		return servicioDesafioUno.prueba(nombre);
		
	}*/
	
	@RequestMapping(value="/fechasFaltantes", produces = { "application/json" }, method=RequestMethod.GET)
	public PeriodoResponse fechasFaltantes( ) {
		return servicioDesafioUno.fechasFaltantes();
		
	}
}
