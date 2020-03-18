package com.previred.rest.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.previred.json.ApiPeriodosJson;

public class PeriodosApiClient {

	public ApiPeriodosJson getPeriodos() throws Exception {
		
		URL url = new URL("http://localhost:8080/periodos/api");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if (conn.getResponseCode() != 200) 
			throw new Exception("error al obtener la información desde el servicio");

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		Gson gson = new Gson();
		ApiPeriodosJson fromJson = gson.fromJson(br, ApiPeriodosJson.class);
			
		System.out.println("JSON IN:" + gson.toJson(fromJson));
			
		return fromJson;
		
		
	}
	
	
}
