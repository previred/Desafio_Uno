/**
 * 
 */
package com.previred.periodos.misolucion.comandos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.misolucion.dominio.Periodo;
import com.previred.periodos.misolucion.dominio.PeriodoResultado;
import com.previred.periodos.misolucion.servicios.CalculaPeriodoService;
import com.previred.periodos.misolucion.servicios.ClienteGDDService;

/**
 * @author Leonardo Silva Bustos
 *
 */
@Component
public class ProcesadorDeComandos {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcesadorDeComandos.class);
	
	private static final String PARAM_ARCHIVO_DE_ENTRADA="entrada";
	private static final String PARAM_ARCHIVO_DE_SALIDA="salida";
	
	private final ObjectMapper objectMapper;
	private final CalculaPeriodoService calculaPeriodoService;
	private final ClienteGDDService clienteGDDService;
	private final String defaultArchivoSalida;
	
	@Value("classpath:result-template.txt")
	private Resource resource;
	
	public ProcesadorDeComandos(ObjectMapper objectMapper, CalculaPeriodoService calculaPeriodoService,
			ClienteGDDService clienteGDDService, @Value("${result.file}") String defaultArchivoSalida){
		this.objectMapper = objectMapper;
		this.calculaPeriodoService = calculaPeriodoService;
		this.clienteGDDService = clienteGDDService;
		this.defaultArchivoSalida = defaultArchivoSalida;
	}
	
	public void process(ApplicationArguments args) {
		
		try {
			if(tratarArchivos(args)) {			
				this.procesarArchivos(args);			
			} else {			
				this.procesaApi();			
			}
		} finally {
			LOGGER.info("Fin de aplicacion por comandos");
			System.exit(1);
		}
		
		
	}
	
	private String readTemplate() {
		try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            LOGGER.error("Error al leer el template: {}", e.getMessage());
        }
		return null;
	}
	
	private void procesaApi() {
		LOGGER.info("Procesa desde api");
		Periodo periodo = clienteGDDService.consultaService();
		PeriodoResultado resultado = generaResultado(periodo);		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.defaultArchivoSalida))){
			LOGGER.debug("Crear archivo: {}", this.defaultArchivoSalida);
			String template = readTemplate();
			template = template.replaceAll("__CREACION__", resultado.getFechaCreacion().toString());
			template = template.replaceAll("__FIN__", resultado.getFechaFin().toString());
			template = template.replaceAll("__FECHAS_RECIBIDAS__", Arrays.toString(resultado.getFechas().toArray()));
			template = template.replaceAll("__FECHAS_FALTANTES__", Arrays.toString(resultado.getFechasFaltantes().toArray()));
		    writer.write(template);
		     
		    writer.close();
		} catch (IOException e) {
			LOGGER.warn("Error al leer el archivo de salida: ", e.getMessage());
		}
	}
	
	private void procesarArchivos(ApplicationArguments args) {
		LOGGER.info("Procesa desde archivos");
		Periodo periodo = null;
		File entrada = null;
		try {			
			entrada = new File(args.getOptionValues(PARAM_ARCHIVO_DE_ENTRADA).get(0));
			periodo = objectMapper.readValue(entrada, Periodo.class);
			PeriodoResultado resultado = generaResultado(periodo);			
			String pathArchivoSalida = args.getOptionValues(PARAM_ARCHIVO_DE_SALIDA).get(0);
			LOGGER.debug("Escribiendo archivo de salida: {}", pathArchivoSalida);
			File archivoSalida = new File(pathArchivoSalida);
			archivoSalida.setWritable(true);
			archivoSalida.delete();
			objectMapper.writeValue(archivoSalida, resultado);
			
		} catch (IOException e) {
			LOGGER.warn("Error al leer el archivo de entrada: ", e.getMessage());
		}
	}
	
	private PeriodoResultado generaResultado(Periodo periodo) {
		PeriodoResultado resultado = new PeriodoResultado();
		
		resultado.setId(periodo.getId());
		resultado.setFechaCreacion(periodo.getFechaCreacion());
		resultado.setFechaFin(periodo.getFechaFin());
		resultado.setFechas(periodo.getFechas());
		
		List<LocalDate> fechasFaltantes = calculaPeriodoService.fechasFaltantes(periodo);
		
		resultado.setFechasFaltantes(fechasFaltantes);
		return resultado;
	}
	
	private boolean tratarArchivos(ApplicationArguments args) {
		return args.containsOption(PARAM_ARCHIVO_DE_ENTRADA) && args.containsOption(PARAM_ARCHIVO_DE_SALIDA);
	}
	
}
