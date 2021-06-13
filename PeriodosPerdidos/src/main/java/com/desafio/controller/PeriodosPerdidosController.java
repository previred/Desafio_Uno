package com.desafio.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.object.PeriodosDeserializer;
import com.desafio.object.PeriodosPerdidos;
import com.desafio.service.GeneradorDatos;
import com.desafio.util.CalcularDiferencias;

/**
 * Controlador Rest para obetener los periodos perdidos
 * @author alejandro.cabezas05@gmail.com
 */
@RestController
public class PeriodosPerdidosController {
	private final static Logger LOGGER = Logger.getLogger(PeriodosPerdidosController.class.getName());
		
	
	    @GetMapping("/PeriodosPerdidos")
	    public PeriodosPerdidos data(){
	    	GeneradorDatos datos = new GeneradorDatos();
	    	PeriodosDeserializer periodosDeserializer = new PeriodosDeserializer();
	    	PeriodosPerdidos responseJson = new PeriodosPerdidos();
	    	CalcularDiferencias cal = new CalcularDiferencias();
	    	try {
	    		//se obtienen los datos deserealizados de la api GDD y creamos el nuevo objeto para ser enviado al cliente
	    		periodosDeserializer = datos.getDatosGenerados();		    		
		    	responseJson.setId(periodosDeserializer.getId());
		    	responseJson.setFechaCreacion(periodosDeserializer.getFechaCreacion());
		    	responseJson.setFechas(periodosDeserializer.getFechas());
		    	responseJson.setFechaFin(periodosDeserializer.getFechaFin());	
		    	//se obtiene el arreglo de fechas faltantes
		    	responseJson.setFechasFaltantes(cal.ListaEntreFechas(periodosDeserializer.getFechaCreacion(),periodosDeserializer.getFechaFin(),periodosDeserializer.getFechas()));
	    	}catch (Exception e) {
	    		 LOGGER.log(Level.INFO, "Error:"+e.getMessage());
	    		//al momento de consumir la api GDD si tiene error se guarda en el atributo error
	    		 LOGGER.log(Level.INFO, periodosDeserializer.getError());
			}
	        return responseJson;
	    }	
}
