package com.previred.prueba.service;

import java.time.LocalDate;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.previred.prueba.model.FechasFaltantesType;
import com.previred.prueba.model.GddType;

/**
 * Este servicio se encarga de llamar al GDD y resolver las fechas perdidas en el rango respondido por la API externa.
 * @author fcaballero
 *
 */
@EnableCircuitBreaker
@Service
public class PeriodosPerdidosServiceImpl implements PeriodosPerdidosService {
	
	@Value( "${gdd.url}" )
	private String gddUrl;
	
	@Value("${gdd.timeout.connection}")
	private Duration gddToConn;
	
	@Value("${gdd.timeout.read}")
	private Duration gddToRead;
	
	RestTemplate restTemplate;
	
	
	public PeriodosPerdidosServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
								.setConnectTimeout(gddToConn)
								.setReadTimeout(gddToRead)
								.build();
	}

	private static final Logger logger = LoggerFactory.getLogger(PeriodosPerdidosService.class);
	
	/**
	 * 
	 * @return :FechasFaltantesType la estructura de datos devuelta por GDD incluyendo las fechas faltantes
	 * 
	 */
	@HystrixCommand(fallbackMethod = "circuitLock",
					
					threadPoolProperties = {
							@HystrixProperty(name = "coreSize", value = "10"),
							@HystrixProperty(name = "maxQueueSize", value = "20")
					})
	@Override
	public FechasFaltantesType consultarPeriodosPerdidos() {
		
		FechasFaltantesType fechasFaltantesResponse = new FechasFaltantesType();
		
		// Consultar servicio GDD
		
		GddType gddResponse = restTemplate.getForObject(gddUrl, GddType.class);
		logger.debug(gddResponse.toString());
		
		// Mapping
		fechasFaltantesResponse.setId(gddResponse.getId());
		fechasFaltantesResponse.setFechaCreacion(gddResponse.getFechaCreacion());
		fechasFaltantesResponse.setFechaFin(gddResponse.getFechaFin());
		fechasFaltantesResponse.setFechas(gddResponse.getFechas());
		
		// Calculo de fechas faltantes
		logger.debug("Fecha de inicio: " + gddResponse.getFechaCreacion());
		logger.debug("Fecha de inicio: " + gddResponse.getFechaFin());
		
		// Validar si la fecha de creacion es posterior a la de fin.
		if(gddResponse.getFechaCreacion().isAfter(gddResponse.getFechaFin())) {
			logger.error("La fecha de creacion recibida del GCC es anterior o igual a la fecha de termino");
			throw new RuntimeException("La fecha de creacion recibida del GCC es anterior o igual a la fecha de termino");
		}
		
		int countExist = 0;
		int countNoExist = 0;
		
		// Busqueda de periodos faltantes
	    for (LocalDate pivot = gddResponse.getFechaCreacion(); !pivot.isAfter(gddResponse.getFechaFin()); pivot = pivot.plusMonths(1)) {
	      if(fechasFaltantesResponse.getFechas().lastIndexOf(pivot) < 0) {
	    	  fechasFaltantesResponse.getFechasFaltantes().add(pivot);
	    	  countNoExist++;
	    	  logger.debug("Fecha evaluada: " + pivot + ", No existe, incluida");
	      }
	      else {
	    	  countExist++;
	    	  logger.debug("Fecha evaluada: " + pivot + ", Ya existe");
	      }
	    }
		
	    // Log para monitoreo, logstash + elastic + kibana por ejemplo
	    logger.info("Cantidad de fechas recibidas GDD," + gddResponse.getFechas().size());
	    logger.info("Cantidad de fechas evaluadas," + (countExist + countNoExist));
	    logger.info("Cantidad de fechas existentes," + countExist);
	    logger.info("Cantidad de fechas no existentes," + countNoExist);
	    
		return fechasFaltantesResponse;
	}
	
	/**
	 * Metodo para el cierre del ciruito
	 * @return FechasFaltantesType
	 * @throws Exception 
	 */
	public FechasFaltantesType circuitLock() throws Exception {
			logger.info("Circuito cerrado");
			throw new Exception("Circuito cerrado, implementar accion de contingencia");
			//return new FechasFaltantesType(0, null, null, null, null);
	}
}
