package com.previred.periodos.desafio.service;

import com.previred.periodos.desafio.model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by sati on 23-03-20.
 */
@Service
public class ClienteRestGDDService {

    private static final String BODY = "body";

    @Value("${endpoint.gdd}")
    private String endpoint;

    @Autowired
    private RestTemplate rsTemplate;

    public Periodo getPeriodos(){
        ResponseEntity<Periodo>rs=rsTemplate.exchange(endpoint,HttpMethod.GET,this.getHttpAcceptHeader(),Periodo.class);
        return rs.getBody();
    }

    private HttpEntity<String> getHttpAcceptHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(BODY, headers);
    }
}
