package org.pr3v1r3d.desafio1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.pr3v1r3d.desafio1.client.model.GddResponse;
import org.pr3v1r3d.desafio1.model.PeriodosPerdidosResponse;
import org.pr3v1r3d.desafio1.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PeriodosPerdidosService {

    Logger logger = LoggerFactory.getLogger(PeriodosPerdidosService.class);

	@Value("${service.rest.gdd.url}")
	private String gddUrl;
	
	@Autowired
	private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	public PeriodosPerdidosResponse periodosPerdidos() throws Exception {
		PeriodosPerdidosResponse periodosPerdidosResponse = new PeriodosPerdidosResponse();
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
	    
	    periodosPerdidosResponse.setId(id);
	    periodosPerdidosResponse.setFechaCreacion(gddResponse.getFechaCreacion());
	    periodosPerdidosResponse.setFechaFin(gddResponse.getFechaFin());
	    periodosPerdidosResponse.setFechas(gddResponse.getFechas());
	    periodosPerdidosResponse.setFechasFaltantes(missingDates);
	    
	    return periodosPerdidosResponse;
	}

	//TODO: move to rest-consumes component
	private GddResponse callGdd() throws Exception {
		try {
			GddResponse gddResponse = restTemplate.getForObject(gddUrl, GddResponse.class);
			logger.info(gddResponse.toString());
		    return gddResponse;
		} catch(Exception e) {
			throw new Exception("El servicio Generador de Datos no esta disponible");
		}
	}

}
