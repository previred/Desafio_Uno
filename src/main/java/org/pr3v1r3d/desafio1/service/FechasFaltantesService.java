package org.pr3v1r3d.desafio1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.pr3v1r3d.desafio1.client.model.GddResponse;
import org.pr3v1r3d.desafio1.model.date.FechasFaltantesResponse;
import org.pr3v1r3d.desafio1.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FechasFaltantesService {

    Logger logger = LoggerFactory.getLogger(FechasFaltantesService.class);

	@Value("${service.rest.gdd.url}")
	private String gddUrl;
	
	@Autowired
	private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	public FechasFaltantesResponse fechasFaltantes() {
		FechasFaltantesResponse fechasFaltantesResponse = new FechasFaltantesResponse();
		GddResponse gddResponse = callGdd();
	    List<LocalDate> missingDates = new ArrayList<LocalDate>();
	    DateUtil.findMissingDates(
	    		gddResponse.getFechaCreacion(),
	    		gddResponse.getFechaFin(),
	    		gddResponse.getFechas(),
	    		missingDates,
	    		0);
	    //TODO: move to repository component
	    jdbcTemplate.execute("INSERT INTO LOG_REQ(DT) VALUES (CURRENT_TIMESTAMP);");
	    Long id = jdbcTemplate.queryForObject("CALL SCOPE_IDENTITY();", Long.class);
	    
	    fechasFaltantesResponse.setId(id);
	    fechasFaltantesResponse.setFechaCreacion(gddResponse.getFechaCreacion());
	    fechasFaltantesResponse.setFechaFin(gddResponse.getFechaFin());
	    fechasFaltantesResponse.setFechas(gddResponse.getFechas());
	    fechasFaltantesResponse.setFechasFaltantes(missingDates);
	    
	    return fechasFaltantesResponse;
	}

	//TODO: move to rest-consumes component
	private GddResponse callGdd() {
		GddResponse gddResponse = restTemplate.getForObject(gddUrl, GddResponse.class);
		logger.info(gddResponse.toString());
	    return gddResponse;
	}

}
