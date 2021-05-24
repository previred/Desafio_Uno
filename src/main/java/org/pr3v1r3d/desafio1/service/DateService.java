package org.pr3v1r3d.desafio1.service;

import java.util.ArrayList;
import java.util.List;

import org.pr3v1r3d.desafio1.client.model.GddResponse;
import org.pr3v1r3d.desafio1.model.date.PopulateMissingResponse;
import org.pr3v1r3d.desafio1.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DateService {

	@Value("${rest-service.gdd.url}")
	private String gddUrl;
	
	@Autowired
	private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	public PopulateMissingResponse populateMissing() {
		PopulateMissingResponse populateMissingResponse = new PopulateMissingResponse();
		GddResponse gddResponse = callGdd();
	    //System.out.println(gddResponse);
	    List missingDates = new ArrayList();
	    DateUtil.findMissingDates(
	    		gddResponse.getFechaCreacion(),
	    		gddResponse.getFechaFin(),
	    		gddResponse.getFechas(),
	    		missingDates,
	    		0);
	    //TODO: move to repository component
	    jdbcTemplate.execute("INSERT INTO LOG_REQ(DT) VALUES (CURRENT_TIMESTAMP);");
	    Long id = jdbcTemplate.queryForObject("CALL SCOPE_IDENTITY();", Long.class);
	    populateMissingResponse.setId(id);
	    populateMissingResponse.setInitDate(gddResponse.getFechaCreacion());
	    populateMissingResponse.setFinalDate(gddResponse.getFechaFin());
	    populateMissingResponse.setDates(gddResponse.getFechas());
	    populateMissingResponse.setMissingDates(missingDates);
	    
	    return populateMissingResponse;
	}

	//TODO: move to rest-consumes component
	private GddResponse callGdd() {
		GddResponse gddResponse = restTemplate.getForObject(gddUrl, GddResponse.class);
	    return gddResponse;
	}

}
