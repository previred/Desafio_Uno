package com.api_desafio1.util;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component("servicioUtil")
public class ServicioUtil {

	public ClientResponse get(String url) {
		
		WebResource webResource = getWebResource(url);
		 
		return webResource.accept("application/json").type("application/json").accept("application/json").get(ClientResponse.class);

	}
	
	public ClientResponse post(String url, String input) {
		
        WebResource webResource = getWebResource(url);
        
        return webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
    }
    
    private WebResource getWebResource(String url) {
    	
        Client client = Client.create();
        
        return client.resource(url);
    }
}
