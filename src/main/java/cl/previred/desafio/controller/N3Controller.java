package cl.previred.desafio.controller;

import cl.previred.desafio.service.N3Service;
import cl.previred.desafio.vo.Solucion;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/desafiouno")
public class N3Controller {

	@Autowired
	private N3Service service;

	/**
	 * Endpoint que retorna un {@link ResponseEntity} de {@link Solucion} para el problema planteado Nivel3
	 * @return
	 */
	@GetMapping("/nivel3")
	public ResponseEntity<Solucion> resolucionNivel3() {

		ResponseEntity<Solucion> respuesta;
		Solucion solucion = new Solucion();
		solucion = service.obtenerSolucion();
		try {
			respuesta = new ResponseEntity<>(solucion, HttpStatus.OK);
		}catch (FeignException e) {
			System.out.println("PROBLEMA INTERNO DE COMUNICACION");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Throwable e) {
			System.out.println("PROBLEMA A REVISAR");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return respuesta;
	}
}
