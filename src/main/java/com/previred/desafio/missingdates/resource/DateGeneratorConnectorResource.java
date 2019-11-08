package com.previred.desafio.missingdates.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.missingdates.connector.DateGeneratorConnector;
import com.previred.desafio.missingdates.model.DateInfo;

@RestController
@CrossOrigin(origins="*")
public class DateGeneratorConnectorResource {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateGeneratorConnectorResource.class);
	
	@Autowired
	DateGeneratorConnector dateGeneratorConnector;
	
	@GetMapping(value="/v1/periodos", produces="application/json")
	@ResponseBody
	public HttpEntity<DateInfo> getDateInfo(){
		DateInfo dateinfo = null;
		try {
			dateinfo = dateGeneratorConnector.getDateInfo();
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dateinfo);
	}
	
}
