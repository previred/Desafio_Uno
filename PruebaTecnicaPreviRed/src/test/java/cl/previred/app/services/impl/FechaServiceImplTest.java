package cl.previred.app.services.impl;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cl.previred.app.client.IGeneradorDeDatos;
import cl.previred.app.data.dto.ResponseDto;
import cl.previred.app.errores.ErrorConexcionApiException;
import cl.previred.app.errores.ErrorGlobalSistema;

public class FechaServiceImplTest {

	private static final String URL = "http://127.0.0.1:8080/periodos/api";
	private static final String DATA = "{\"id\":1,\"fechaCreacion\":\"2003-05-01\",\"fechaFin\":\"2015-04-01\",\"fechas\":[\"2003-05-01\",\"2003-06-01\",\"2003-07-01\",\"2003-08-01\",\"2003-10-01\",\"2003-11-01\",\"2003-12-01\",\"2004-01-01\",\"2004-02-01\",\"2004-03-01\",\"2004-04-01\",\"2004-05-01\",\"2004-06-01\",\"2004-08-01\",\"2004-09-01\",\"2004-10-01\",\"2004-11-01\",\"2004-12-01\",\"2005-01-01\",\"2005-02-01\",\"2005-03-01\",\"2005-04-01\",\"2005-05-01\",\"2005-06-01\",\"2005-08-01\",\"2005-09-01\",\"2005-10-01\",\"2005-11-01\",\"2006-03-01\",\"2006-05-01\",\"2006-07-01\",\"2006-08-01\",\"2006-09-01\",\"2006-10-01\",\"2006-12-01\",\"2007-01-01\",\"2007-02-01\",\"2007-05-01\",\"2007-07-01\",\"2007-09-01\",\"2007-11-01\",\"2008-01-01\",\"2008-02-01\",\"2008-03-01\",\"2008-05-01\",\"2008-07-01\",\"2008-08-01\",\"2008-09-01\",\"2008-11-01\",\"2008-12-01\",\"2009-01-01\",\"2009-02-01\",\"2009-03-01\",\"2009-05-01\",\"2009-06-01\",\"2009-07-01\",\"2009-11-01\",\"2009-12-01\",\"2010-01-01\",\"2010-05-01\",\"2010-07-01\",\"2010-08-01\",\"2010-10-01\",\"2010-11-01\",\"2011-02-01\",\"2011-03-01\",\"2011-04-01\",\"2011-07-01\",\"2011-10-01\",\"2011-11-01\",\"2012-01-01\",\"2012-02-01\",\"2012-03-01\",\"2012-04-01\",\"2012-09-01\",\"2012-10-01\",\"2012-11-01\",\"2012-12-01\",\"2013-01-01\",\"2013-02-01\",\"2013-03-01\",\"2013-05-01\",\"2013-07-01\",\"2013-08-01\",\"2013-09-01\",\"2013-11-01\",\"2013-12-01\",\"2014-01-01\",\"2014-03-01\",\"2014-05-01\",\"2014-07-01\",\"2014-08-01\",\"2014-10-01\",\"2014-11-01\",\"2015-02-01\",\"2015-03-01\"]}";

	ResponseDto RESPONSE = new ResponseDto();

	private static final long ID = 1L;
	private static final String FECHA_CREACION = "1988-02-01";
	private static final String FECHA_FIN = "2013-08-01";
	private static final List<String> FECHAS = new ArrayList<String>(0);
	private static final List<String> FECHAS_FALTANTE = new ArrayList<String>(0);

	@Mock
	IGeneradorDeDatos gdd;

	@InjectMocks
	FechaServiceImpl fechaServiceImpl;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		RESPONSE.setId(ID);
		RESPONSE.setFechaCreacion(FECHA_CREACION);
		RESPONSE.setFechaFin(FECHA_FIN);
		RESPONSE.setFechas(FECHAS);
		RESPONSE.setFechasFaltante(FECHAS_FALTANTE);
		
		Mockito.when(gdd.getDataApi(URL)).thenReturn(DATA);

	}
	
	

	@Test
	public void getFechasRestantesTest() throws Exception {
		Mockito.when(gdd.getDataApi(URL)).thenReturn(DATA);
		fechaServiceImpl.getFechasRestantes();
	}
	@Test(expected = RuntimeException.class)
	public void getFechasRestantesRuntimeExceptionErrorTest() throws Exception {
		Mockito.when(gdd.getDataApi(URL)).thenThrow(new RuntimeException());
		
	}
	@Test(expected = ConnectException.class)
	public void getFechasRestantesConnectExceptionErrorTest() throws Exception {
		Mockito.when(gdd.getDataApi(URL)).thenThrow(new ConnectException());
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void getFechasRestantesIllegalArgumentExceptionTest() throws Exception{
		Mockito.doThrow(IllegalArgumentException.class).when(gdd).getDataApi(URL);
	}
	
	@Test(expected = ErrorGlobalSistema.class)
	public void getFechasRestantesNullPointerExceptionTest() throws Exception{
		Mockito.doThrow(NullPointerException.class).when(gdd).getDataApi(URL);
	}
	
	@Test(expected = Exception.class)
	public void getFechasRestantesExceptionTest() throws Exception {
		Mockito.doThrow(Exception.class).when(gdd).getDataApi(URL);
	}

	@Test(expected = ErrorGlobalSistema.class)
	public void getFechasRestantesErrorGlobalSistemaTest() throws Exception {
		Mockito.doThrow(ErrorGlobalSistema.class).when(gdd).getDataApi(URL);
	}

	@Test(expected = ErrorConexcionApiException.class)
	public void getFechasRestantesErrorConexcionApiExceptionTest() throws Exception {
		Mockito.doThrow(ErrorConexcionApiException.class).when(gdd).getDataApi(URL);
	}
	
	
	
}
