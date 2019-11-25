package com.cox.igdd.repository;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cox.igdd.models.OutListadoFechas;
import com.cox.igdd.util.GenerarFechasFaltantes;
import com.cox.igdd.util.Recursos;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Repository
public class SolicitarFechasRepoImpl implements SolicitarFechasRepo {

	@Autowired
	private OutListadoFechas outFechas;
	@Autowired
	private GenerarFechasFaltantes generarFechasFaltantes;
	ObjectMapper objectMapper = new ObjectMapper();
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SolicitarFechasRepoImpl.class);

	public OutListadoFechas invocar() {
		// TODO Auto-generated method stub

		com.mashape.unirest.http.HttpResponse<String> response = null;

		try {
			response = Unirest.get(Recursos.URL_GDD).header("accept", "application/json").asString();
			outFechas = objectMapper.readValue(response.getBody(), OutListadoFechas.class);
			outFechas.setFechasFaltantes(generarFechasFaltantes.obtenerDiferencias(outFechas.getFechaCreacion(),outFechas.getFechaFin(), outFechas.getFechas()));

			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return outFechas;
	}

}
