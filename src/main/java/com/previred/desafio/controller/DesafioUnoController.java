package com.previred.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.service.DesafioUnoService;

/**
 * Clase tipo controlador para definicion de endpoint de servicio
 * @author Claudio Marambio Cespedes.
 * @since 1.0
 */
@RestController
@RequestMapping("/desafioUno")
public class DesafioUnoController {
	
	/**
	 * Metodo de endpoint para acceder al nivel 3 del desafio el cual 
	 * obtiene las fechas faltantes.
	 * @author Claudio Marambio Cespedes.
	 * @return String adaptado bajo estructura json.
	 * @since 1.0
	 */
	@GetMapping("/nivel3")
	@ResponseStatus(HttpStatus.OK)
	public String obtieneFechasFaltantes() throws Exception{
		DesafioUnoService serv = new DesafioUnoService();
		return serv.obtenerFechFaltantes();
	}
	
	
	
}
