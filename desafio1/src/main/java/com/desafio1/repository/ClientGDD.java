package com.desafio1.repository;

import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;;
import com.desafio1.model.*;
import org.springframework.beans.factory.annotation.Value;


@Repository
public class ClientGDD {
	
	@Value("${restservice.periodos.url}")
    private String url;

	public Periodo getperiodos() throws Exception {
			
		RestTemplate restTemplate = new RestTemplate();
		Periodo perido = restTemplate.getForObject( this.url , Periodo.class);
		
		return perido;
	}
	

}
