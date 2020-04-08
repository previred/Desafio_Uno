package com.previred.periodos.backend.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 *
 * @author arojas
 */
@Service
public class GeneradorDeDatoServiceImp implements GeneradorDeDatoService {

	final static Logger logger = Logger.getLogger(GeneradorDeDatoServiceImp.class);

	@Value("${api.periodos}")
	private String apiPeriodos;
	@Value("${api.header}")
	private String header;
	@Value("${api.header.value}")
	private String headerValue;

	@Override
	public String getGDD() {
		String data = null;
		try {
			logger.info("*********************");
			logger.info("** URL = " + apiPeriodos);
			logger.info("*********************");
			URL url = new URL(apiPeriodos);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestProperty(header, headerValue);
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			data = root.toString();
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
		}
		return data;
	}
}
