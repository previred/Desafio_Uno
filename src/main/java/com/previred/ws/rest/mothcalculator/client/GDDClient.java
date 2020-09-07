package com.previred.ws.rest.mothcalculator.client;

import com.previred.ws.rest.mothcalculator.models.Period;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GDDClient {

    private RestTemplate restTemplate;

    public GDDClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Period getPeriod() {
        ResponseEntity<Period> periodResponse = this.restTemplate.getForEntity("http://127.0.0.1:8080/periodos/api", Period.class);
        return periodResponse.getBody();
    }
}
