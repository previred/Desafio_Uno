package com.previred.desafio.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.model.Fechas;

/**
 * Clase tipo cliente para la el consumo de servicio gdd.
 * @author Claudio Marambio Cespedes.
 * @since 1.0
 */

public class GddClient {
	
	/*
	 * Instancia privada para RestTemplate.
	 */
	@Autowired
	RestTemplate restTempl = new RestTemplate();
	
	/*
	 * Variable de tipo Fechas
	 */
	private Fechas fechaGdd;
	
	/*
	 * Variable que contiene el endpoint de generacion de fechas.
	 */
	public static final String END_POINT_GDD="http://localhost:8080/periodos/api";
	
	/**
	 * Metodo que obtiene generacion de fechas desde sevicio.
	 * @author Claudio Marambio Cespedes.
	 * @return Fechas, objeto de salida que contiene estructura de json de entrada.
	 * @since 1.0
	 */
	public Fechas obtieneGDD() throws Exception{
		try {
			String json = restTempl.getForObject(END_POINT_GDD, String.class);
			ObjectMapper fech = new ObjectMapper();
			fechaGdd = fech.readValue(json, Fechas.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fechaGdd;
	}
}
