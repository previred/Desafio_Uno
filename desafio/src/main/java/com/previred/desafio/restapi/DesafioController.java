package com.previred.desafio.restapi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.previred.desafio.model.Periodo;
import com.previred.desafio.model.PeriodoRespuesta;
import com.previred.desafio.utils.ProcesaFechas;

@RestController
public class DesafioController {
	private static final Logger log = LoggerFactory.getLogger(DesafioController.class);
	private static final String ENDPOINT = "http://127.0.0.1:8080/periodos/api";
	@GetMapping("/solucion")
	public ResponseEntity<PeriodoRespuesta> solucion() {

		RestTemplate restTemplate = new RestTemplate();

		URI uri = null;
		Periodo periodo = new Periodo();
		PeriodoRespuesta periodoRespuesta = new PeriodoRespuesta();
		try {
			uri = new URI(ENDPOINT);

			ResponseEntity<Periodo> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<Periodo>() {
					});
			periodo = response.getBody();

			ProcesaFechas procesarFechas = new ProcesaFechas(periodo);
			List<LocalDate> fechasFaltantes = procesarFechas.extraeFechasFaltantes();

			periodoRespuesta.setId(periodo.getId());
			periodoRespuesta.setFechaCreacion(periodo.getFechaCreacion());
			periodoRespuesta.setFechaFin(periodo.getFechaFin());
			periodoRespuesta.setFechas(periodo.getFechas());
			periodoRespuesta.setFechasFaltantes(fechasFaltantes);

			
			log.info(periodoRespuesta.toString());
			
			
			creaArchivo(periodoRespuesta.toString());
			
			
			return new ResponseEntity<>(periodoRespuesta, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
    }
	
	private void creaArchivo(String respuesta) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resultado.txt"))) {
			writer.write(respuesta);
		}
	}
	
}

