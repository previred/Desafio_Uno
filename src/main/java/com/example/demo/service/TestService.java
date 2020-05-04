package com.example.demo.service;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Periodo;
import com.google.gson.Gson;

@Service
public class TestService {

	public String testService() throws IOException {
		
		
		URL myUrl = new URL("http://localhost:8080/periodos/api");
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        
        con.setRequestMethod("GET");

        
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        System.out.println(con.getRequestMethod());

        InputStream is;
        
        if (con.getResponseCode() >= 400) {
            is = con.getErrorStream();
        } else {
            is = con.getInputStream();
        }

        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder response = new StringBuilder();
        for (inputLine = input.readLine(); inputLine != null; inputLine = input.readLine()) {
            response.append(inputLine);
        }
        input.close();
        
        //previsualizamos la respuesta
        System.out.println("" + response.toString());
        
        Periodo periodo = new Gson().fromJson(response.toString(), Periodo.class);
        
        int meses[] = { 0,1,2,3,4,5,6,7,8,9,10,11 };
       
        ArrayList<Date> fechasFaltantes = new ArrayList<Date>();
        
        Calendar calendar = Calendar.getInstance();
    	for(Date fecha : periodo.getFechas()) {
    		
    		for(int i: meses) {
    			
        		calendar.setTime(fecha);
        	    calendar.add(Calendar.MONTH, i + 1);
	    	    
	        	if(!periodo.getFechas().contains(calendar.getTime())) {
	        		fechasFaltantes.add(calendar.getTime());
	        	}
	        	
	        	periodo.setFechasFaltantes(fechasFaltantes);
        	}
    	}
        	
        	
        Gson gson = new Gson();
        
        String json = gson.toJson(periodo);
        
        return json;
	}
}
