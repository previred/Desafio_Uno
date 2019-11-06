package com.previred.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.connection.ConnectionCDD;
import com.previred.entity.CDDResponse;
import com.previred.entity.ServiceResponse;

public class ServiceBusiness {
	
	public String init() {
		String jsonEntrada = getCDDJson();
		return getResponse(jsonEntrada);
	}
	
	String getResponse(String json) {
		try {
			ObjectMapper obj = new ObjectMapper();
			CDDResponse entrada = obj.readValue(json, CDDResponse.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			
			List<String> fechasEntregadasList = new ArrayList<>();
			Set<String> fechasEntregadas = new HashSet<>();
			String fechaAuxStr = null;
			
			for(String fecha : entrada.getFechas()) {
				fechasEntregadasList.add(fecha);
				fechasEntregadas.add(fecha);
			}
			
			List<String> fechasFaltantes = new ArrayList<>();
			Date fechaAux = sdf.parse(entrada.getFechaCreacion());
			
			while(!sdf.parse(entrada.getFechaFin()).before(fechaAux)) {
				fechaAuxStr = sdf.format(fechaAux);			
				if(!fechasEntregadas.contains(fechaAuxStr)) {
					fechasFaltantes.add(fechaAuxStr);
				}				
				fechaAux = addMonth(fechaAux);  
			}			
			
			ServiceResponse salida = new ServiceResponse();		
			salida.setId(entrada.getId());
			salida.setFechaCreacion(entrada.getFechaCreacion());
			salida.setFechaFin(entrada.getFechaFin());
			salida.setFechas(fechasEntregadasList);
			salida.setFechasFaltantes(fechasFaltantes);
			
			return obj.writeValueAsString(salida);
		} catch (Exception e) {
			System.out.println("ServiceBusiness.getResponse: " + e.getMessage());
		}
		return null;
	}

	private Date addMonth(Date fechaAux) {
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(fechaAux);    
		myCal.add(Calendar.MONTH, +1);
		fechaAux = myCal.getTime();
		return fechaAux;
	}
	
	String getCDDJson() {
		ConnectionCDD conn = new ConnectionCDD();		
		return conn.callCDD();
//		return new DummyCDD().getProc();
	}
}

//class DummyCDD {
//	String getProc() {
//
//		int id = 6;
//		String fechaCreacionStr = "1969-03-01";
//		String fechaFinStr = "1970-01-01";
//		String[] fechasArr = { "1969-03-01", "1969-05-01", "1969-09-01", "1970-01-01" };
//
//		CDDResponse entrada = new CDDResponse();
//		try {
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			entrada.setId(id);
//			entrada.setFechaCreacion(sdf.parse(fechaCreacionStr));
//			entrada.setFechaFin(sdf.parse(fechaFinStr));
//			List<String> fechas = new ArrayList();
//			for (String fecha : fechasArr) {
//				fechas.add(fecha);
//			}
//			entrada.setFechas(fechas);
//			ObjectMapper obj = new ObjectMapper();
//			return obj.writeValueAsString(entrada);
//
//		} catch (Exception e) {
//
//		}
//		return null;
//	}
//}

