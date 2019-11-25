package com.previred.periodosperdidos.servicio;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodosperdidos.exception.GeneracionArchivoException;
import com.previred.periodosperdidos.servicio.model.PeriodosPerdidos;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * @author Jorge San Martin
 *
 */
public class GeneradorArchivo {
	
	@Value("${periodosperdidos.archivo.salida}")
	private String nombreArchivo;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Genera archivo json con los Periodos y Periodos Perdidos
	 * @param periodos perdidos
	 * @throws Exception 
	 */
	public void generar(PeriodosPerdidos p) throws Exception {
		
		if (p == null) {
			log.error("No se han proporcionado datos para la generacion del archivo");
			throw new Exception("No se han proporcionado datos para la generacion del archivo");
		}
						
		log.info("Generando archivo " + nombreArchivo + "...");
		try {
			objectMapper.writeValue(new File(nombreArchivo), p);
		} catch (IOException e) {
			log.error("Se ha producido el siguiente error al escribir el archivo: e", e);
			throw new GeneracionArchivoException();
		}
		log.info("Archivo generado exitosamente");
	}

}
