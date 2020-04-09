package com.previred.gddfull.service.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.gddfull.swagger.model.Periodo;
import com.previred.gddfull.utils.PeriodosUtil;

@Service
public class PeriodosRestImpl implements PeriodosService {
	
	@Value("${gdd.service.url}")
	private String urlRestService;

	@Override
	public Periodo getPeriodos() {
	    RestTemplate template = new RestTemplate();
	    Periodo response = template.getForObject(urlRestService, Periodo.class);
	    List<LocalDate> fullDates=PeriodosUtil.getPeriodosBetweenDates(response.getFechaCreacion(), response.getFechaFin());
	    List<LocalDate> missingDates=PeriodosUtil.minusList(fullDates, response.getFechas());
	    response.setFechasFaltantes(missingDates);
	    return response;
	}

}
