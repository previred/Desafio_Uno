package com.previred.periodos.gddfaltantes.service;

import com.previred.periodos.gddfaltantes.model.Periodo;
import com.previred.periodos.gddfaltantes.model.PeriodoFaltante;
import com.previred.periodos.gddfaltantes.utils.PeriodoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeriodosFaltantesService {
    private RestTemplate restTemplate;

    @Value("${url-api-periodos}")
    private String urlApIPeriodos;

    @Autowired
    public PeriodosFaltantesService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = new RestTemplate();
    }

    public PeriodoFaltante getPeriodoFaltante() {
        Periodo periodo = restTemplate.getForObject(urlApIPeriodos, Periodo.class);
        PeriodoFaltante periodoFaltante = new PeriodoUtils(periodo).getPeriodoFaltantes();
        return periodoFaltante;
    }

}
