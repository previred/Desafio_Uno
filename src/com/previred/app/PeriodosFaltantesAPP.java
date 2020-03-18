package com.previred.app;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.previred.json.ApiPeriodosJson;
import com.previred.rest.client.PeriodosApiClient;
import com.previred.utils.FechasUtils;
import com.previred.utils.FileUtils;

public class PeriodosFaltantesAPP {

	/**
	 * Operación que obtiene como resultado un archivo resumen de las fechas faltantes en un periodo
	 * dado por un servicio generador de datos.
	 * @throws Exception 
	 */
	public void obtenerPeriodosFaltantes() throws Exception {
		
		PeriodosApiClient client = new PeriodosApiClient();
		FechasUtils fAPP = new FechasUtils();
		FileUtils fileAPP = new FileUtils();
		
		//invocamos el servicio rest para obtener la información de los periodos 
		ApiPeriodosJson periodos = client.getPeriodos();
		
		//se obtienen las fechas de inicio y fin
		LocalDate fechaCreacion = fAPP.toLocalDate(periodos.getFechaCreacion());
		LocalDate fechaFin = fAPP.toLocalDate(periodos.getFechaFin());
		
		//se carga la lista de todos los meses del periodo
		List<LocalDate> fechasListaCompleta = fAPP.getFechasListaCompleta(fechaCreacion, fechaFin);
		
		//se carga la lista con las fechas de entrada indicadas por el servicio
		List<LocalDate> fechasEntrada = fAPP.getLocalDatesFromDatesStr(periodos.getFechas());
		
		//se obtiene el resultado de las fechas faltantes
		List<LocalDate> fechasFaltantes = fAPP.getFechasFaltantes(fechasListaCompleta, fechasEntrada);
		
		//se escribe la salida a un archivo de texto plano
		fileAPP.escribirSalida(fechasFaltantes, fechasEntrada, fechaCreacion, fechaFin);
		
		//se construye json para imprimir la salida requerida en el la actividad desafio uno.
		Gson gson = new Gson();
		ApiPeriodosJson json = new ApiPeriodosJson();
		json.setId(periodos.getId());
		json.setFechaCreacion(periodos.getFechaCreacion());
		json.setFechaFin(periodos.getFechaCreacion());
		json.setFechas(fAPP.getStringDatesFromLocalDates(fechasFaltantes));
		String json2 = gson.toJson(json);
		System.out.println("JSON OUT:" + json2);
		
	}
	
}
