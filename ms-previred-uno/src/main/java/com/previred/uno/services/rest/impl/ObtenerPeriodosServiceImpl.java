package com.previred.uno.services.rest.impl;

import com.previred.uno.exceptions.PreviredException;
import com.previred.uno.models.periodos.PeriodosRest;
import com.previred.uno.services.rest.ObtenerPeriodosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.Arrays;

/**
 * Clase de implementación de ObtenerPeriodosService
 * encargada de comunicación con Api externa
 *
 * @author pvillar
 */

@Service
@Slf4j
public class ObtenerPeriodosServiceImpl implements ObtenerPeriodosService {

    private final String urlService;
    private final int readTimeout;
    private final int connectTimeout;

    @Autowired
    public ObtenerPeriodosServiceImpl(@Value("${uri.path.periodos.api}") String urlService,
                                      @Value("${periodos.api.connect.timeout}") int connectTimeout,
                                      @Value("${periodos.api.read.timeout}") int readTimeout){
        this.urlService = urlService;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Override
    public PeriodosRest getPeriodos() throws PreviredException {

        log.info("Configurando llamado rest a : {}", urlService );
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try{
            ResponseEntity<PeriodosRest> response = restTemplate.exchange(urlService, HttpMethod.GET, entity, PeriodosRest.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("Respuesta obtenida : {}", response.getBody().toString());
                return response.getBody();
            }else{
                log.error("Response Status Code NOK : {} - {}", response.getStatusCode(), urlService);
                throw new PreviredException(PreviredException.CodeException.HTTP_STATUS_NOK,
                        "HttpStatus code NOK : " + response.getStatusCode() + " : " + urlService);
            }

        }catch(HttpClientErrorException exception){ // Errores 4xx
            log.error("HttpClientErrorException al consumir : {}", urlService, exception);
            throw new PreviredException(PreviredException.CodeException.ERROR_RESPONSE,
                   "HttpClientErrorException al consumir : " + urlService,
                   exception);
        }catch(HttpServerErrorException exception) { // Errores 5xx
            log.error("HttpServerErrorException al consumir : {}", urlService, exception);
            throw new PreviredException(PreviredException.CodeException.ERROR_RESPONSE_SERVER,
                    "HttpServerErrorException al consumir : " + urlService,
                    exception);
       }catch(Exception exception){// Errores no esperados
            log.error("Exception al consumir : {}", urlService, exception);
            throw new PreviredException(PreviredException.CodeException.ERROR_NO_CONTROLADO,
                    "Exception al consumir  : " + urlService,
                    exception);
        }
    }
}
