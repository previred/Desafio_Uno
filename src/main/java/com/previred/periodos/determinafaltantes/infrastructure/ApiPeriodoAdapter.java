package com.previred.periodos.determinafaltantes.infrastructure;

import com.previred.periodos.determinafaltantes.core.Periodo;
import com.previred.periodos.determinafaltantes.core.PeriodoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
public class ApiPeriodoAdapter implements PeriodoPort {
    private final RestTemplate restTemplate;
    private final ApiPeriodoAdapterConfiguration config;

    @Override
    public Periodo getFechasPeriodoAleatorio() {
        Periodo periodo = null;
        try {
            periodo = restTemplate.getForObject(config.getUrl().toString(), Periodo.class);
        } catch (Exception e) {
            log.error("Error obteniendo las fechas de ApiPeriodo", e);
        }
        if (periodo == null)
            periodo = Periodo.PERIODO_NULO;
        return periodo;
    }
}
