package cl.previred.periodos.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.periodos.dao.PeriodosPerdidosDAO;
import cl.previred.periodos.dto.PeriodoDTO;
import cl.previred.periodos.dto.PeriodosPerdidosResponseDTO;
import cl.previred.periodos.service.PeriodosPerdidosService;
import cl.previred.periodos.util.MapperUtil;
import cl.previred.periodos.utils.Utils;

@Service
public class PeriodosPerdidosServiceImpl implements PeriodosPerdidosService {
	
	Logger logger = LoggerFactory.getLogger(PeriodosPerdidosServiceImpl.class);
	
	@Autowired
	PeriodosPerdidosDAO periodosPerdidosDAO;
	
	@Override
	public PeriodosPerdidosResponseDTO getPeriodosPerdidos() {
		
		logger.info("Obteniendo fechas generadas aleatoriamente");
		PeriodoDTO listaPeriodosInicialGenerador = periodosPerdidosDAO.obtenerPeriodosGeneradorDatos();
		logger.info("Fechas obtenidas exitosamente");
		
		logger.info("Obteniendo fechas faltantes");
		List<LocalDate> listaPeriodosPerdidos = ordenaFechas(obtenerPeriodosPerdidos(listaPeriodosInicialGenerador));
		logger.info("Fechas Faltantes Obtenidas");
		
		logger.info("Generando archivo de respuesta");
		PeriodosPerdidosResponseDTO response = MapperUtil.toPeriodosPerdidosResponseDTO(listaPeriodosInicialGenerador
				,listaPeriodosPerdidos);
		logger.info("Archivo de respuesta generado exitosamente");
		
		return response;
	}

	private List<LocalDate> ordenaFechas(@Valid List<LocalDate> fechas) {
		return fechas.stream().sorted().collect(Collectors.toList());
	}

	private List<LocalDate> obtenerPeriodosPerdidos(PeriodoDTO listaPeriodosInicialGenerador) {
		List<LocalDate> listaFechasTotal = Utils.generarTotalFechasInicioMes(listaPeriodosInicialGenerador.getFechaCreacion()
				, listaPeriodosInicialGenerador.getFechaFin());
		
		return listaFechasTotal.stream()
				.filter(not(listaPeriodosInicialGenerador.getFechas()::contains))
				.collect(Collectors.toList());
	}
	
	public static <R> Predicate<R> not(Predicate<R> predicate) {
	    return predicate.negate();
	}


}
