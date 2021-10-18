package com.previred.desafiouno.service.impl;

import com.previred.desafiouno.dto.PeriodosDTO;
import com.previred.desafiouno.dto.PeriodosPerdidosDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;
import com.previred.desafiouno.service.PeriodoService;
import com.previred.desafiouno.util.RestClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodoServiceImpl implements PeriodoService {

	@Value("${url.service.gdd}")
	private String urlServicioGDD;

	@Override
	public PeriodosPerdidosDTO getPeriodosFaltantesFromGDD() throws FechaCreacionNulaException, FechaFinNulaException, RestClientException {

		PeriodosDTO periodosDTO = RestClientUtil.getPeriodosFromGDD(urlServicioGDD);
		List<String> fechasFaltantes = calculaFechasFaltantes(periodosDTO.getFechaCreacion(), periodosDTO.getFechaFin(), periodosDTO.getFechas());
		return new PeriodosPerdidosDTO(periodosDTO, fechasFaltantes);
	}

	private List<String> calculaFechasFaltantes(String fechaCreacion, String fechaFin, List<String> fechas) throws FechaCreacionNulaException, FechaFinNulaException {
		if(fechaCreacion == null || fechaCreacion.isEmpty()){
			throw new FechaCreacionNulaException("Fecha de creacion obtenida desde GDD viene nula o vacia");
		}
		if(fechaFin == null || fechaFin.isEmpty()){
			throw new FechaFinNulaException("Fecha de creacion obtenida desde GDD viene nula o vacia");
		}
		long mesesPeriodo = ChronoUnit.MONTHS.between(LocalDate.parse(fechaCreacion).withDayOfMonth(1), LocalDate.parse(fechaFin).withDayOfMonth(1));

		List<String> fechasFaltantes = new ArrayList<>();
		LocalDate fechaEval = LocalDate.parse(fechaCreacion);
		for(int i=0; i <= mesesPeriodo; i++){
			if(!fechas.contains(fechaEval.toString())){
				fechasFaltantes.add(fechaEval.toString());
			}
			fechaEval = fechaEval.plus(1,ChronoUnit.MONTHS );
		}

		return fechasFaltantes;
	}
}
