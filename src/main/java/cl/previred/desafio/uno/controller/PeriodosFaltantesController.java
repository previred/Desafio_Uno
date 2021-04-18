package cl.previred.desafio.uno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.desafio.uno.service.PeriodoService;
import cl.previred.desafio.uno.vo.PeriodoFaltante;

@RestController
@RequestMapping("/periodos")
public class PeriodosFaltantesController {

	@Autowired
	private PeriodoService service;

	@GetMapping("/faltantes")
	public ResponseEntity<PeriodoFaltante> faltantes() {

		try {
			return ResponseEntity.ok().body(service.getPeriodosFaltantes());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
