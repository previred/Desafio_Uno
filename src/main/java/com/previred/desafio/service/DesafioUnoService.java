package com.previred.desafio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.client.GddClient;
import com.previred.desafio.model.Fechas;
import com.previred.desafio.model.FechasFaltantes;

/**
 * Clase de servicio para logica de generacion de fechas faltantes.
 * @author Claudio Marambio Cespedes.
 * @since 1.0
 */

public class DesafioUnoService {
	
	public static final String SRV_NO_DISPONIBLE="Servicio de generacion de datos no disponible";
	/**
	 * Metodo que obtiene fechas faltantes.
	 * @author Claudio Marambio Cespedes.
	 * @return String adaptado bajo estructura json.
	 * @since 1.0
	 */
	 public String obtenerFechFaltantes() throws Exception {
			Fechas fecha = 	obtenerFechas();
			if(fecha != null) {
				LocalDate fechaIni = LocalDate.parse(fecha.getFechaCreacion());
				LocalDate fechaFin = LocalDate.parse(fecha.getFechaFin());
				List<String> filtro = new ArrayList<>();

				for (String fec: fecha.getFechas()){
					filtro.add(fec);
				}
				
				List<String> rangoFecha = new ArrayList<>();

				for(LocalDate fechas =  fechaIni.plusMonths(-1); fechas.isBefore(fechaFin); 
						fechas = fechas.plusMonths(1)) {
					    rangoFecha.add(fechas.plusMonths(1).toString());
				}
				Collections.sort(rangoFecha);
				rangoFecha.removeAll(filtro);
				
				FechasFaltantes fechFal = new FechasFaltantes();
				fechFal.setId(fecha.getId());
				fechFal.setFechaCreacion(fechaIni.toString());
				fechFal.setFechaFin(fechaFin.toString());
				fechFal.setFechas(filtro);
				fechFal.setFechasFaltantes(rangoFecha);
				
				return parseJson(fechFal);
			}
			else
			{
				return SRV_NO_DISPONIBLE;
			}
			
	 }

	/**
	 * Metodo que obtiene fechas desde servicio gdd en objeto Fechas
	 * @author Claudio Marambio Cespedes.
	 * @return Fechas, fechas generadas desde servicio.
	 * @since 1.0
	 */
	 public Fechas obtenerFechas() throws Exception{
		 GddClient gdd = new GddClient();
		 return gdd.obtieneGDD();
	 }
	 
	/**
	 * Metodo que da estructura json a objeto por medio de jackson.
	 * @author Claudio Marambio Cespedes.
	 * @return String, con estructura json.
	 * @since 1.0
	 */
	 public String parseJson(FechasFaltantes fechFaltantes){
		 ObjectMapper obm = new ObjectMapper();
		 String json = "";
			try {
				json = obm.writeValueAsString(fechFaltantes);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		return json;
		 
	 }
	 
	 

}
