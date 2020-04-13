package com.previred.periodos.client;

import com.previred.periodos.dto.PeriodoDto;
import com.previred.periodos.exception.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiPeriodoClientImpl implements ApiPeriodoClient {

    private RestTemplate restTemplate;

    @Value("${periodos.endpoint.fechas}")
    private String urlPeriodos;

    @Value("${error.periodoService}")
    private String errorPeriodoService;

    @Autowired
    private ApiPeriodoClientImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public PeriodoDto ejecutar() {
        try {
            ResponseEntity<PeriodoDto> response = restTemplate.getForEntity(urlPeriodos, PeriodoDto.class);
            if(response.getStatusCode() != HttpStatus.OK)
                throw new ServerErrorException(errorPeriodoService);
            return response.getBody();
        }catch (Exception ex){
            throw new ServerErrorException(errorPeriodoService);
        }
    }
}
