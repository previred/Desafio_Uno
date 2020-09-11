package cl.previred.app.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cl.previred.app.data.dto.ResponseDto;


public class FechaTest {

	public static final String FORMAT_YYYYMMDD_ISO = "yyyy-MM-dd";
	public static final String FECHA_OK = "2019-05-10";
	
	Locale locale = new Locale("es", "CL");

	
	ResponseDto RESPONSE_DTO = new ResponseDto();

	private static final long ID = 1L;
	private static final String FECHA_CREACION = "1988-02-01";
	private static final String FECHA_FIN = "2013-08-01";
	private static final List<String> FECHAS = new ArrayList<String>(0);
	private static final List<String> FECHAS_FALTANTE = new ArrayList<String>(0);
	
	
	
	@Before
	public void init() {
		System.out.println("inicio del testing");
		
		RESPONSE_DTO.setId(ID);
		RESPONSE_DTO.setFechaCreacion(FECHA_CREACION);
		RESPONSE_DTO.setFechaFin(FECHA_FIN);
		RESPONSE_DTO.setFechas(FECHAS);
		RESPONSE_DTO.setFechasFaltante(FECHAS_FALTANTE);
	}
	
	
	@Test
	public void getLocalDateTimeStringTest() throws Exception, IllegalArgumentException {
		LocalDate start = LocalDate.parse(FECHA_OK, DateTimeFormatter.ofPattern(FORMAT_YYYYMMDD_ISO, locale));
		String fecha = Fecha.getLocalDateTimeString(start);
		assertEquals(FECHA_OK, fecha);
		assertNotNull(fecha);
	}
	
	@Test
	public void getlistadoFechasFaltantesTest() throws Exception, IllegalArgumentException {
		List<String> lista = Fecha.listadoFechasFaltantes(RESPONSE_DTO);
		assertNotNull(lista);
	}


	@After
	public void end() {
		System.out.println("final del testing");
	}
	

}
