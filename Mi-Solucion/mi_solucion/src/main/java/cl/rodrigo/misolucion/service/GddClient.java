package cl.rodrigo.misolucion.service;

import java.net.ConnectException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.rodrigo.misolucion.bean.ResponseGDD;

public class GddClient {
	private static final String URL ="http://127.0.0.1:8080/periodos/api";
	private static final String NOMBRE ="Accept";
	private static final String VALOR ="application/json";
	private static final String JSONMAPPINGEXCEPTION_ERROR="Ha ocurrido un error al intentar leer la respuesta del servicio "+URL;
	private static final String JSONPROCESSINGEXCEPTION_ERROR="Ha ocurrido un error al intentar leer la respuesta del servicio "+URL;
	private static final String MSJ_LOG_ERROR ="Se Ha tenido un problema con la conexión al servicio "+URL;
	private static final String MSJ_LOG_ERROR_GENERAL ="Ha ocurrido un error inesperado al llamar al servicio "+URL;
	
	public static ResponseGDD cliGDD() {
		RestTemplate restTempl = new RestTemplate();
		ResponseGDD response = new ResponseGDD();
		ObjectMapper om = new ObjectMapper();
		HttpHeaders header =new HttpHeaders();
		header.set(NOMBRE, VALOR);
		
		try {
		HttpEntity request= new HttpEntity(header);
		ResponseEntity<String> respGDD= restTempl.exchange(URL,HttpMethod.GET , request, String.class);
		System.out.println(respGDD.getBody());
		
			response = om.readValue(respGDD.getBody().toString(), ResponseGDD.class);
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
			System.out.println(JSONMAPPINGEXCEPTION_ERROR);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
			System.out.println(JSONPROCESSINGEXCEPTION_ERROR);
		}catch(ResourceAccessException e) {
			System.out.println(e.getMessage());
			System.out.println(MSJ_LOG_ERROR);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(MSJ_LOG_ERROR_GENERAL);
		}
		return response;
		
		
	}
	
	
}
