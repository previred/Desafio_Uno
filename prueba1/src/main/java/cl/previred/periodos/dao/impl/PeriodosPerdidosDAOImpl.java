package cl.previred.periodos.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.previred.periodos.client.GeneradorDeDatosClient;
import cl.previred.periodos.dao.PeriodosPerdidosDAO;
import cl.previred.periodos.dto.PeriodoDTO;
import cl.previred.periodos.exception.PreviredBusinessException;
import cl.previred.periodos.exception.PreviredTechnicalException;

@Component
public class PeriodosPerdidosDAOImpl implements PeriodosPerdidosDAO {
	
	public static Logger logger = LoggerFactory.getLogger(PeriodosPerdidosDAOImpl.class);
	
	@Autowired
	GeneradorDeDatosClient periodosPerdidosClient;
	
	@Override
	public PeriodoDTO obtenerPeriodosGeneradorDatos() {
		PeriodoDTO response;
		try {
			logger.info("Realizar llamado a GDD con servicio original");
			response = periodosPerdidosClient.obtenerPeriodosGeneradorDatos();
			logger.info("Obtencion de datos desde GDD exitoso");
		} catch (Exception e) {
			//TODO implementar Hystrix llamando a servicio dummy
			logger.info("Ha ocurrido un error al comuinicarse con servicio GDD Original");
			throw new PreviredTechnicalException("PREVIRED-TCH-01: ERROR AL COMUNICARSE A SERVICIO GDD", e.getMessage());
		}
		
		if(response == null)
			throw new PreviredBusinessException("PREVIRED-BS-01","Comunicacion GDD no devuelve respuesta");
		
		return response;
	}

}
