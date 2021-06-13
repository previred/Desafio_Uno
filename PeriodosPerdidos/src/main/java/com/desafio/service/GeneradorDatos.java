package com.desafio.service;


 

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.desafio.controller.PeriodosPerdidosController;
import com.desafio.object.PeriodosDeserializer;
import com.desafio.util.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Clase utilizada para conectar con la api GDD
 * @author alejandro.cabezas05@gmail.com

 * Retorna el objeto PeriodosDeserializer
 * @return 
 */
public class GeneradorDatos {
	private final static Logger LOGGER = Logger.getLogger(PeriodosPerdidosController.class.getName());
	public PeriodosDeserializer getDatosGenerados() {		
		String responseJson = "";
		PeriodosDeserializer periodoObj = new PeriodosDeserializer();
		try {
			String URI = "http://127.0.0.1:8080/periodos/api";
			Client client = ClientBuilder.newClient();
			
			WebTarget target = client.target(URI);
			Invocation.Builder solicitud = target.request().accept("application/json");
			Response response = solicitud.get();
			responseJson = response.readEntity(String.class);		
			LOGGER.log(Level.INFO, "Periodos API:"+responseJson);
			final Gson gson = new GsonBuilder().setPrettyPrinting()
			        .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
			        .create();
			
			periodoObj = gson.fromJson(responseJson, PeriodosDeserializer.class);
		} catch (Exception e) {
			// TODO: handle exception
			periodoObj.setError("ERROR:"+ e.getMessage());
		}
		return periodoObj;
	}
}
