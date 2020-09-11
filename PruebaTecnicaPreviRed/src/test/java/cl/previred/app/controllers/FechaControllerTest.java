package cl.previred.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import cl.previred.app.data.dto.ResponseDto;
import cl.previred.app.services.IFechaService;

public class FechaControllerTest {
	
	private static final int SUCCEES_HTTP_OK = 200;
	ResponseDto RESPONSE = new ResponseDto();
	
	private static final long ID=1L;
	private static final String FECHA_CREACION="1988-02-01";
	private static final String FECHA_FIN="2013-08-01";
	private static final List<String> FECHAS = new ArrayList<String>(0);
	private static final List<String> FECHAS_FALTANTE = new ArrayList<String>(0);
	
	
	
	@Mock
	IFechaService iFechaService;
	
	@InjectMocks
	FechaController fechaController;
	
	
	@Before
	public void init() {
		System.out.println("Inicio del testeo");
		MockitoAnnotations.initMocks(this);
		RESPONSE.setId(ID);
		RESPONSE.setFechaCreacion(FECHA_CREACION);
		RESPONSE.setFechaFin(FECHA_FIN);
		RESPONSE.setFechas(FECHAS);
		RESPONSE.setFechasFaltante(FECHAS_FALTANTE);
		
		
		Mockito.when(iFechaService.getFechasRestantes()).thenReturn(RESPONSE);
	}
	
	
	@Test
	public void getFechasFaltantesTest() {
		ResponseEntity<?> response = fechaController.getFechasFaltantes();
		assertEquals(response.getBody(), RESPONSE);
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@After
	public void end() {
		System.out.println("Final del testeo");
	}
	
	

}
