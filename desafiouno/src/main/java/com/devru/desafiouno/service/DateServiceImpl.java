package com.devru.desafiouno.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.devru.desafiouno.dto.Periodo;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

@Service
public class DateServiceImpl implements DateService {

	private static final Logger log = LoggerFactory.getLogger(DateServiceImpl.class);
	
	@PostConstruct
	private void init() {
		/** Por si necesito inicializar previamente */
	}	
	
	
	@Override
	public Periodo calcularPeriodos(ObtenerPeriodosResponse response) {
		/*Se prepara el formato de fecha a leer*/
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		/*Se instancian las fechas*/
		YearMonth fechaCreacion = YearMonth.parse(response.getFechaCreacion(), formatter);
		YearMonth fechaFin = YearMonth.parse(response.getFechaFin(), formatter);
		/*Lista de fechas faltantes*/
		List<String> fechasFaltantes = new ArrayList<>();
		
		/*Se valida que la lista de periodos pre-generados por el GDD no sea null*/
		if(response.getFechas() == null || response.getFechas().isEmpty()) {
			response.setFechas(new ArrayList<>());
		}
		while(fechaCreacion.isBefore(fechaFin)) { /*Miestras sea antes de la fecha fin*/
			/*Se incrementa el mes de uno en uno*/
			fechaCreacion = fechaCreacion.plusMonths(1); 
			log.info("fecha calculada: {} ",fechaCreacion+"-01");
			/* Si ya existe en la lista de fechas generadas por GDD (Generador Datos Desafio) servicio externo*/
			if (response.getFechas().contains(fechaCreacion+"-01") || response.getFechaFin().equalsIgnoreCase(fechaCreacion+"-01")) {
				log.info("fecha {} ya existe", fechaCreacion+"-01");
			} else {
				log.info("fecha {} agregada", fechaCreacion+"-01");
				fechasFaltantes.add(fechaCreacion+"-01");
			}
		}
		
		/*Se mapea el objeto con las dos lista de fechas*/
		Periodo periodo = new Periodo();
		periodo.setFechaCreacion(response.getFechaCreacion());
		periodo.setFechaFin(response.getFechaFin());
		periodo.setFechas(response.getFechas());
		periodo.setFechasFaltantes(fechasFaltantes);
		periodo.setId(response.getId());
		return periodo;
	}

}
