package com.app.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.app.dev.FechaUtils;
import com.dto.Periodo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Path("/fechasfaltantes") 
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
public class FechasFaltantes {
	 @GET  
	    public Response getFechasFaltantes() {     
		 Client client = ClientBuilder.newClient().register(new JacksonFeature());
		 Periodo periodo = new Periodo();
		 periodo = client
		 .target("http://127.0.0.1:8080/periodos/api")
		 .request(MediaType.APPLICATION_JSON).get(Periodo.class);
		 //System.out.println("RESPONSE: " + periodo.toString());
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.registerModule(new JavaTimeModule());
		 FechaUtils.getInstance();
		 List<Date> fechasFaltantes = FechaUtils.getFechasFromPeriodo(periodo);
		 periodo.setFechasFaltantes(fechasFaltantes);
		 //System.out.println(fechasFaltantes.toString());
	        return Response.ok(periodo,MediaType.APPLICATION_JSON).build();   
	    } 
}
