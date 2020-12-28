package com.desafiouno.nivel3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafiouno.nivel3.model.ResponseFechasFaltantes;
import com.desafiouno.nivel3.model.ResponsePeriodos;



@Service("FechasFaltantesService")
public class FechasFaltantesImpl implements FechasFaltantesService{
	
	@Value("${urlPeriodos}")
	private String urlPeriodos;
	
	Logger logger = LoggerFactory.getLogger(FechasFaltantesImpl.class);
	
	public ResponseFechasFaltantes obtenerfechasFaltantes(){
		logger.info("Se inicia la obtencion de periodos a la API : " + urlPeriodos);
		RestTemplate restTemplate = new RestTemplate();
		ResponseFechasFaltantes responseFechasFaltantes = new ResponseFechasFaltantes();
		try {
			ResponseEntity<ResponsePeriodos> response = restTemplate.getForEntity(urlPeriodos, ResponsePeriodos.class);
			logger.info("Se inicia el seteo de parametors para la respuesta de fechas faltantes");
			responseFechasFaltantes.setParametrosResponsePeriodos(response.getBody());
			
		} catch (Exception  e) {
			logger.error("Error al obtener periodos favor revisar estado de la api de periodos");
		}
		return responseFechasFaltantes;
	}

}
