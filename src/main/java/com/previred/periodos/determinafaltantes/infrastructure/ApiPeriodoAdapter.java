package com.previred.periodos.determinafaltantes.infrastructure;

import com.previred.periodos.determinafaltantes.core.Periodo;
import com.previred.periodos.determinafaltantes.core.PeriodoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class ApiPeriodoAdapter implements PeriodoPort {
    private final RestTemplate restTemplate;
    private final ApiPeriodoAdapterConfiguration config;

    @Override
    public Periodo getFechasPeriodoAleatorio() {
        Periodo periodo = restTemplate.getForObject(config.getUrl().toString(), Periodo.class);
        if(periodo == null)
            periodo= Periodo.PERIODO_NULO;
        return periodo;
    }
}
