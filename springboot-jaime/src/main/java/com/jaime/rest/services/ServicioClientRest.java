package com.jaime.rest.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaime.rest.model.FechasServicio;

@Service
public class ServicioClientRest {
	
	@Value("${fechasRest.url}")
    private String urlServicio;
	
	public FechasServicio obtenerFechasServicio() { 
		
		try {
			
			URL url = new URL(urlServicio); 
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection(); 
			
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper();
					
			FechasServicio fechas = objectMapper.readValue(response.toString(), FechasServicio.class);	
			
			return fechas; 
			
		} catch ( Exception ex ) { 
			System.err.println(ex.getMessage());
			throw new RuntimeException(ex.getMessage()); 
		}
		
	}
	
}
