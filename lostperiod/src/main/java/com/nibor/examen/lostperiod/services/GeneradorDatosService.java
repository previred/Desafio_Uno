package com.nibor.examen.lostperiod.services;

import com.nibor.examen.lostperiod.dtos.PeriodoGeneradoDTO;
import com.nibor.examen.lostperiod.exceptions.GeneralServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GeneradorDatosService {

    String urlGeneradorPeriodos = "http://localhost:8080/periodos/api";

    public PeriodoGeneradoDTO getPeriodosGenerado(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(urlGeneradorPeriodos , PeriodoGeneradoDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
