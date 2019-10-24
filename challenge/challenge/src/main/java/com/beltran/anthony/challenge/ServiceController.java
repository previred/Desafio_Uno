package com.beltran.anthony.challenge;

import com.beltran.anthony.challenge.model.Periodo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ServiceController {
    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    public Periodo getPeriodos() throws Exception {
        Periodo periodo = call(new RestTemplate());
        return periodo;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public Periodo call(RestTemplate restTemplate) throws Exception {
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
        Periodo periodo = restTemplate.getForObject("http://127.0.0.1:8080/periodos/api", Periodo.class);
        periodo.setFechasFaltantes(new ArrayList<>());
        LocalDate fecha_inicial = periodo.getFechaCreacion();
        do {
            if (!periodo.getFechas().contains(fecha_inicial)) {
                periodo.getFechasFaltantes().add(fecha_inicial);
            }
            fecha_inicial = fecha_inicial.plusMonths(1l);
        } while (!fecha_inicial.isAfter(periodo.getFechaFin()));
        return periodo;
    }
}
