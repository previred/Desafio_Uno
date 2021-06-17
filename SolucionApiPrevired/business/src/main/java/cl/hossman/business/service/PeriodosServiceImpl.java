package cl.hossman.business.service;
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
import cl.hossman.connector.client.ClienteRest;
import cl.hossman.connector.constants.ConnectorConstants;
import cl.hossman.business.constants.BusinessConstants;
import cl.hossman.business.exceptions.BusinessException;
import cl.hossman.connector.exceptions.ClienteRestException;
import cl.hossman.entity.model.Periodo;
import cl.hossman.business.utils.FechaUtil;


@Service("periodoService")
public class PeriodosServiceImpl implements PeriodosService{	

	Logger log = LoggerFactory.getLogger(PeriodosServiceImpl.class);

	/***
	 *  @author Hossman Escobar (H.E).
	 *  @param Periodo = objeto que se le quieren asignar las fechas faltantes.
	 *  @return periodo = el mismo objeto de entrada con las fechas faltantes asignadas.

	 * Asigna las fechas faltantes al objeto periodo y lo retorna
	 */

	public Periodo calculaFechasFaltantes(Periodo periodo) { 

		final int MESES_A_SUMAR = 1; /*H.E en este caso solo tratamos meses a sumar*/

		/*H.E: Saco las fechas que me dio GDD, para que asi solo queden las faltantes, aplicando teoria de conjuntos:  TODAS_LAS_FECHAS - FECHAS_GDD = FECHAS_FALTANTES */
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
	 *  @author Hossman Escobar (H.E).
	 *  @param objectMapper = objectMapper a utilizar.
	 *  @return env = Enviroment del restcontroller.

	 * Abre la conexion y mapea el resultado a un objeto del modelo de esta api
	 * @throws BusinessException 
	 */

	public Periodo getPeriodosExternos(ObjectMapper objectMapper, Environment env) throws BusinessException { 
		Periodo periodo = null;
		
		try {
			ClienteRest con = new ClienteRest(env);			
			/*H.E: Conexion a la api externa */
			ResponseEntity<String> periodoEntity = con.conectorApiRestExterna(HttpMethod.GET, ConnectorConstants.API_PERIOD);
			periodo  = objectMapper.readValue(periodoEntity.getBody(), Periodo.class); /* H.E: Mapeo Respuesta a clase del modelo */	
			return calculaFechasFaltantes(periodo);
		} catch (ClienteRestException e) {
			throw new BusinessException(BusinessConstants.CODE_ERR_CONSUMO_EXTERNO,BusinessConstants.MSG_ERR_CONSUMO_EXTERNO,e);
		} catch (IOException e) {
			throw new BusinessException(BusinessConstants.CODE_ERR_SERIALIZACION,BusinessConstants.MSG_ERR_SERIALIZACION,e);  
		}
		
	}

}
