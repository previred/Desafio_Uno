package com.previred.periodosperdidos.servicio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.periodosperdidos.client.IPeriodosClient;
import com.previred.periodosperdidos.swagger.codegen.model.Periodo;

@Service
public class PeriodosPerdidosService {
	
	@Autowired
	private IPeriodosClient client;
	
	public Periodo obtenerPeriodosPerdidos() {
		Periodo periodoGdd = client.periodos();
		periodoGdd.setFechasFaltantes(determinarPeriodosPerdidos(periodoGdd));
		return periodoGdd;
	}
	
	private List<LocalDate> determinarPeriodosPerdidos(Periodo p){
		Map<LocalDate,LocalDate> fechasGeneradasMap = p.getFechas().stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
		List<LocalDate> resp = new ArrayList<LocalDate>();
		for (LocalDate aux = p.getFechaCreacion(); aux.compareTo(p.getFechaFin()) <= 0; aux = aux.plus(1, ChronoUnit.MONTHS)) {
			if (!fechasGeneradasMap.containsKey(aux)){
				resp.add(aux);
			}
		}
		return resp;
		
	}
	

}
