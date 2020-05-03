package com.hossman.periodos.service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hossman.periodos.cliente.PeriodoRestClient;
import com.hossman.periodos.swagger.codegen.exception.ApiException;
import com.hossman.periodos.swagger.codegen.exception.ClienteRestException;
import com.hossman.periodos.swagger.codegen.exception.ServiceException;
import com.hossman.periodos.swagger.codegen.model.Periodo;
import com.hossman.periodos.utils.ApiConstants;
import com.hossman.periodos.utils.FechaUtil;


@Service("periodoService")
public class PeriodosServiceImpl implements PeriodosService{	

	Logger log = LoggerFactory.getLogger(PeriodosServiceImpl.class);

	/***
	 *  @author Hossman Escobar (H.E)
	 *  @param Periodo = objeto que se le quieren asignar las fechas faltantes
	 *  @return periodo = el mismo objeto de entrada con las fechas faltantes asignadas

	 * Asigna las fechas faltantes al objeto periodo y lo retorna
	 */

	public Periodo calculaFechasFaltantes(Periodo periodo) { 

		final int MESES_A_SUMAR = 1; /*H.E en este caso solo tratamos meses a sumar*/

		/*H.E: Saco las fechas que me dio GDD, para que asi solo queden las faltantes,  TODAS_LAS_FECHAS - FECHAS_GDD = FECHAS_FALTANTES */
		List<LocalDate> fechasGeneradasDeInicioAfin = FechaUtil.generadorDeFechasMeses(periodo.getFechaCreacion(), periodo.getFechaFin(), MESES_A_SUMAR);

		/*HE Orden de complejidad (n) */
		for (LocalDate fecha : periodo.getFechas()) { 

			fechasGeneradasDeInicioAfin.remove(fecha);
		}

		/* H.E Un array vacio es una posible respuesta, para el negocio indicaría que no hay fechas faltantes */
		periodo.setFechasFaltantes(fechasGeneradasDeInicioAfin);
		return periodo;
	}
	
	/***
	 *  @author Hossman Escobar (H.E)
	 *  @param objectMapper = objectMapper a utilizar
	 *  @return env = Enviroment del restcontroller

	 * Abre la conexion a la apiRest y mapea el resultado a un objeto del modelo de esta api
	 * @throws ApiException 
	 */

	public Periodo getPeriodosExternos(ObjectMapper objectMapper, Environment env) throws ServiceException { 
		Periodo periodo = null;
		
		try {
			PeriodoRestClient con = new PeriodoRestClient(env);			
			/*H.E: Abro conexion a la api externa */
			ResponseEntity<String> periodoEntity = con.conectorApiRestExterna(HttpMethod.GET, ApiConstants.API_PERIOD);
			periodo  = objectMapper.readValue(periodoEntity.getBody(), Periodo.class); /* H.E: Mapeo Respuesta a clase del modelo */	
			return calculaFechasFaltantes(periodo);
		} catch (ClienteRestException e) {
			log.error("Ocurrio un error al consumr el cliente rest", e);
			throw new ServiceException("Ocurrio un error al consumr el cliente rest");
		} catch (IOException e) {
			log.error("Error al usar el mapper de json a java object", e);
			throw new ServiceException("Error de serialización del objeto");  
		}
		
	}

}
