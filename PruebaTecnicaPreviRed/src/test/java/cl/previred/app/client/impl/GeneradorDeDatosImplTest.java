package cl.previred.app.client.impl;

import static org.junit.Assert.assertNotNull;
import java.net.ConnectException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class GeneradorDeDatosImplTest {
	
	private static final String URL = "http://127.0.0.1:8080/periodos/api";

	private static final String ERROR = "Failed : HTTP Error code :";
	private static final String KEY = "Accept";
	private static final String METODO = "GET";
	private static final String VALUE = "application/json";

	
	GeneradorDeDatosImpl INSTANCIA = new GeneradorDeDatosImpl();
	
	@Before
	public void init() throws Exception {
		System.out.println("iniciando el test con Junit");
		INSTANCIA.setError(ERROR);
		INSTANCIA.setKey(KEY);
		INSTANCIA.setMetodo(METODO);
		INSTANCIA.setValue(VALUE);
	}
	
	@Test
	public void getDataApiTest() throws Exception, RuntimeException, ConnectException {
		String respuesta = INSTANCIA.getDataApi(URL);
		assertNotNull(respuesta);
	}
	
	@After
	public void end() {
		System.out.println("Final del test de Junit");
	}
	
	
	
	

}
