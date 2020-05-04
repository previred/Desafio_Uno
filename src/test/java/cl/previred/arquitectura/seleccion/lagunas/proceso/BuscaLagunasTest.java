package cl.previred.arquitectura.seleccion.lagunas.proceso;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;
import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BuscaLagunasTest {


	@Test
	public void validaBusqueda() throws JsonGenerationException, JsonMappingException, IOException 
	{
		InputVO datai= new InputVO();
		datai.setId(8);
		LocalDate fechaCreacion = LocalDate.of(1969, Month.MARCH, 1);
		datai.setFechaCreacion(fechaCreacion );
		LocalDate fechaFin = LocalDate.of(1970, Month.JANUARY, 1);
		datai.setFechaFin(fechaFin );
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fechaCreacion);
		fechas.add(fechaFin);
		LocalDate fechai = LocalDate.of(1969, Month.MAY, 1);
		fechas.add(fechai);
		fechai = LocalDate.of(1969, Month.SEPTEMBER, 1);
		fechas.add(fechai);
		datai.setFechas(fechas);
		
		OutputVO x = BuscaLagunas.calcula(datai);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		StringWriter stringEmp = new StringWriter();
		objectMapper.writeValue(stringEmp, x);
		System.out.println("Data Output :\n"+stringEmp);
		

		OutputVO datao=objectMapper.readValue(stringEmp.toString(), OutputVO.class);
		
		assertTrue("Los Id no son iguales.", datai.getId().intValue()==datao.getId().intValue());
		assertTrue("Las fechas de creacion no son iguales. "+datao.getFechaCreacion(), datai.getFechaCreacion().compareTo(datao.getFechaCreacion())==0);
		assertTrue("Las fechas de fin no son iguales.", datai.getFechaFin().compareTo(datao.getFechaFin())==0);
		
	}
	
	@Test
	public void validaConCanales01() throws EntradaException, SalidaException 
	{
		//Preparamos un objeto
		InputVO datai= new InputVO();
		datai.setId(8);
		LocalDate fechaCreacion = LocalDate.of(1969, Month.MARCH, 1);
		datai.setFechaCreacion(fechaCreacion );
		LocalDate fechaFin = LocalDate.of(1970, Month.JANUARY, 1);
		datai.setFechaFin(fechaFin );
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fechaCreacion);
		fechas.add(fechaFin);
		LocalDate fechai = LocalDate.of(1969, Month.MAY, 1);
		fechas.add(fechai);
		fechai = LocalDate.of(1969, Month.SEPTEMBER, 1);
		fechas.add(fechai);
		datai.setFechas(fechas);
		
		//Preparamos los canales de pruebas
		CanalEntrada ci = new CanalEntrada(datai);
		CanalSalida  co = new CanalSalida();
		
		//Probamos
		BuscaLagunas.busca(ci, co);

		//recuperamos la respuesta
		OutputVO datao=co.getDatos();
		
		//La validamos
		assertTrue("Los Id no son iguales.", datai.getId().intValue()==datao.getId().intValue());
		assertTrue("Las fechas de creacion no son iguales. "+datao.getFechaCreacion(), datai.getFechaCreacion().compareTo(datao.getFechaCreacion())==0);
		assertTrue("Las fechas de fin no son iguales.", datai.getFechaFin().compareTo(datao.getFechaFin())==0);
		
		LocalDate fd= LocalDate.parse("1969-04-01");
		assertTrue("Las primera fecha no corresponde: ["+fd+"!="+datao.getFechasFaltantes().get(0)+"]", datao.getFechasFaltantes().get(0).compareTo(fd)==0);
		
	}
	
	
	

}
