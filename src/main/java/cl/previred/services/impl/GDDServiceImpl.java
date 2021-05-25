package cl.previred.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.constants.AppConstants;
import cl.previred.entity.IncompletePeriod;
import cl.previred.services.GDDService;

@Service
public class GDDServiceImpl implements GDDService{
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LogManager.getLogger(GDDServiceImpl.class);


	public IncompletePeriod getIncompletePeriod() throws IOException {

		logger.info("[IncompletePeriod] Start");
		logger.info("[IncompletePeriod] Endpoint GDD: "+env.getProperty("endpoint.rest.gdd"));
		
		URL url = new URL( env.getProperty("endpoint.rest.gdd"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if (conn.getResponseCode() != AppConstants.SUCCESS_HTTP) {
			throw new RuntimeException("Error en llamada al servicio GDD : Codigo Error HTTP: " + conn.getResponseCode());
		}
		
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(in);
		String output;
		StringBuilder stringBuilder = new StringBuilder();
		
		while ((output = br.readLine()) != null) {
			stringBuilder.append(output);
		}

		conn.disconnect();
		ObjectMapper mapper = new ObjectMapper();

		IncompletePeriod incompletePeriod = mapper.readValue(stringBuilder.toString(), IncompletePeriod.class);
		
		logger.info("[IncompletePeriod] periodoObtenido: "+incompletePeriod.toString());
		logger.info("[IncompletePeriod] Fin");

		return incompletePeriod;
	}

}
