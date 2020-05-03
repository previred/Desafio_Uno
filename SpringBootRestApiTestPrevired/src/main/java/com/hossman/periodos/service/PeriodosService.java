package com.hossman.periodos.service;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hossman.periodos.swagger.codegen.exception.ServiceException;
import com.hossman.periodos.swagger.codegen.model.Periodo;

public interface PeriodosService {
	Periodo calculaFechasFaltantes(Periodo periodo);
	Periodo getPeriodosExternos(ObjectMapper objectMapper, Environment env) throws ServiceException;
}
