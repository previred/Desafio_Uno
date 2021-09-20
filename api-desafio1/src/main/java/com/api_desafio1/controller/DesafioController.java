package com.api_desafio1.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.api_desafio1.dto.*;
import com.api_desafio1.util.RespuestaUtil;
import com.api_desafio1.util.ServicioUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/desafio")
public class DesafioController {

	@Autowired
	private ServicioUtil objServUtil;
	
    private static final Logger objLog = LoggerFactory.getLogger(DesafioController.class);   
    
    @ApiOperation(value = "Retorna fechas faltantes como desafio 1")
    @RequestMapping(value = "/generar", method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses({ @ApiResponse(code = 204, message = "Funcionalidad ejecutada con error", response = HttpStatus.class),
                    @ApiResponse(code = 200, message = "Funcionalidad ejecutada con exito", response = PeriodoDto.class) })
    public ResponseEntity<PeriodoDto> generar() {
     
    	int mesMax = 0;
    	
    	String mesVarCad = "";
    	
    	// Variable estado que indica si la fecha generada se encuentra en la lista de origen entregada por el API de fechas
    	boolean encontrado = false;
    	
    	PeriodoDto objPeriodo = null;
    	
    	// URL API Servicio de fechas
    	String strUrl = "http://localhost:8080/periodos/api";
    	
    	// Arreglo para la nueva lista de fechas faltantes
    	List<LocalDate> lista = new ArrayList<LocalDate>();
    	
    	PeriodoDto objNuevo = new PeriodoDto();
    	        
    	try {
    		
    		ClientResponse response = objServUtil.get(strUrl);
    	    
    		if(response.getStatus() == 200) {

    			String strRsptaServicio = response.getEntity(String.class);
    	        
    			ObjectMapper objMapper = new ObjectMapper();
    			
    			// registro de modulo para el manejo de fechas con soporte JAVA 8 con Jackson
    			objMapper.registerModule(new JavaTimeModule());
    
    			objPeriodo = objMapper.readValue(strRsptaServicio, PeriodoDto.class);
    			
    			objLog.info("fecha creacion:" + objPeriodo.getFechaCreacion().toString());
    			objLog.info("fecha fin:" + objPeriodo.getFechaFin().toString());
    			
    			// Se crea un nuevo objeto a partir con los datos entregados por el API de fechas
    			objNuevo.setId(objPeriodo.getId());
    			objNuevo.setFechaCreacion(objPeriodo.getFechaCreacion());
    			objNuevo.setFechaFin(objPeriodo.getFechaFin());    		
    			objNuevo.setFechas(objPeriodo.getFechas());
    			
    			// Se asigna por separado el año y mes de la fecha de creacion como de la fecha de fin para el mejor manejo en las comparaciones
    			int anoInicio = objPeriodo.getFechaCreacion().getYear();
    			
    			int mesInicio = objPeriodo.getFechaCreacion().getMonthValue();
    			
    			int anoFin = objPeriodo.getFechaFin().getYear();
    			
    			int mesFin = objPeriodo.getFechaFin().getMonthValue();
    			    			    			
    			// Se implementa un ciclo para la generacion de todos los años entre la fecha de creacion y fecha de fin
    			for(int anoVar=anoInicio; anoVar<=anoFin;anoVar++) {
    				    				    				
    				// si el año generado es igual al año fin tomamos se considera generar el periodo hasta el mes anterior de la fecha de fin, en caso contrario generamos todos los periodos del año
    				if (anoVar == anoFin) {
    					mesMax = mesFin - 1;
    				}
    				else {
    					mesMax = 12;
    				}
    				// Se implementa un ciclo para la generacion de todos los periodos o hasta un periodo anterior al maximo segun fecha fin entregada por el API de fechas
    				for(int mesVar=mesInicio+1; mesVar<=mesMax;mesVar++) {
    					
    					// En el caso de existir un mes de un digito se concatena el 0
    					if (mesVar < 10) {
    						mesVarCad = "0".concat(String.valueOf(mesVar));
    					} else {
    						mesVarCad = String.valueOf(mesVar);
    					}
    	    					
    					// Se crea un valor de tipo LocalDate para realizar la comparacion con las fechas entregadas por el API Fechas
    					LocalDate objFechaAEvaluar = LocalDate.parse(String.valueOf(anoVar).concat("-").concat(mesVarCad).concat("-01"));
    					
    					// Siempre se inicia con el valor false el indicador de fecha encontrada
    					encontrado = false;
    					
    					// Se implementa un ciclo para la busqueda de la fecha generada no se encuentre en la lista de fechas entregadas por el API fechas
    					for(LocalDate objFechaOrigen : objPeriodo.getFechas()) {
    						
    						if (objFechaOrigen.getMonthValue() == objFechaAEvaluar.getMonthValue() && objFechaOrigen.getYear() == objFechaAEvaluar.getYear()) {
    							
    							encontrado = true;
    							
    							break;
    						}    						    						
    						
    					}
    					// En el caso de no encontrarse la fecha en la lista original de fechas entregada por el API Fechas, se agrega como fecha faltante
    					if (!encontrado) lista.add(objFechaAEvaluar);
 					
    				}
    				
    				mesInicio = 0;  				
    			}

    		}
    		
    		objNuevo.setFechasFaltantes(lista);
    	}
    	catch (IOException e){
    		objLog.error(e.getMessage());
    	}
         
        return RespuestaUtil.ok(objNuevo);		  
    }
}
