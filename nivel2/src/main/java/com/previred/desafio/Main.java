package com.desafio;


import com.desafio.models.InputModel;
import com.desafio.models.OutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
public class Main implements CommandLineRunner{

		private static final Logger log = LoggerFactory.getLogger(Main.class);
		private static final String url = "http://127.0.0.1:8080/periodos/api";

		public static void main(String[] args) {
			log.info("START");
			SpringApplication.run(Main.class, args);
			log.info("END");
		}

		@Override
		public void run(String... args) throws Exception {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("body", headers);
			log.info("Requesting data from generator service");
			try{
				ResponseEntity<InputModel> responseInput = restTemplate.exchange(url, HttpMethod.GET, entity, InputModel.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				if( HttpStatus.OK.equals(responseInput.getStatusCode())){
					OutputModel outputModel = MissingDates.find(responseInput.getBody());
					
					ArrayList<String> fechas = new ArrayList<>();
					responseInput.getBody().getFechas().forEach(date -> fechas.add(sdf.format(date)));
					
					ArrayList<String> fechasFaltantes = new ArrayList<>();
					outputModel.getFechasFaltantes().forEach(date -> fechasFaltantes.add(sdf.format(date)));
					
					FileWriter fw = new FileWriter("output.txt");
					fw.write("fecha creacion: " + sdf.format(outputModel.getFechaCreacion()) + "\n");
					fw.write("fecha fin: " + sdf.format(outputModel.getFechaCreacion()) + "\n");
					fw.write("fechas recibidas: " + fechas + "\n");
					fw.write("fechas faltantes: " + fechasFaltantes + "\n");
					fw.flush();
					fw.close();
					log.info("File output.txt writen");
				}else{
					log.error("Error getting generator service response ", new Throwable(responseInput.getStatusCode().toString()));
				}
			}catch(Exception ex){
				log.error(ex.getMessage(), ex);
			}
		}
}