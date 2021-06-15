package cl.pabloromero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.pabloromero.model.FechasFaltantesResponse;
import cl.pabloromero.service.DesafioUnoService;

@RestController
public class DesafioUnoController {

	@Autowired
	private DesafioUnoService service;
	
	@GetMapping(path = "/v1/getFechasFaltantes")
	public FechasFaltantesResponse getFechasFaltantes() {
		return service.getFechasFaltantes();
	}
}
