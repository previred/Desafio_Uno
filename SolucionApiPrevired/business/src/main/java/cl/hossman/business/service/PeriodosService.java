package cl.hossman.business.service;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.hossman.business.exceptions.BusinessException;
import cl.hossman.entity.model.Periodo;

public interface PeriodosService {
	/**
	 * Envia a calcular las fechas faltantes
	 * **/
	Periodo calculaFechasFaltantes(Periodo periodo);
	/**
	 *Obtiene los periodos desde otro micro-servicio
	 * **/
	Periodo getPeriodosExternos(ObjectMapper objectMapper, Environment env) throws BusinessException;
}
