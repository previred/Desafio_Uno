package com.desafiouno.nivel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiouno.nivel3.model.ResponseFechasFaltantes;
import com.desafiouno.nivel3.service.FechasFaltantesService;


@RestController
public class Nivel3Controller {
	
	
	
	
	@Autowired
	FechasFaltantesService fechasFaltantesService;
	
	@GetMapping(value = "/fechas/faltantes", headers = "Accept=application/json")
	public ResponseEntity<ResponseFechasFaltantes> getFechasFaltantes() {
		
		return new ResponseEntity<>(fechasFaltantesService.obtenerfechasFaltantes(),
				fechasFaltantesService.obtenerfechasFaltantes().getId() != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
