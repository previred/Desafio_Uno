package com.previred.periodos.configuration;

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosService;
import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase;
import com.previred.periodos.determinafaltantes.core.PeriodoPort;
import com.previred.periodos.determinafaltantes.infrastructure.adapter.primary.error.ErrorControllerAdvice;
import com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary.adapter.secundary.ApiPeriodoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final DeterminaFaltantesProperties properties;

    @Bean
    public ResponseEntityExceptionHandler getResponseEntityExceptionHandler() {
        return new ErrorControllerAdvice();
    }

    @Bean
    public DeterminarPeriodosPerdidosUseCase getDeterminarPeriodosPerdidosUseCase(PeriodoPort periodoPort) {
        return new DeterminarPeriodosPerdidosService(periodoPort);
    }

    @Bean
    public RestTemplate getRestTemplete() {
        return new RestTemplate();
    }

    @Bean
    public PeriodoPort getPeriodoPort(RestTemplate restTemplate) {
        return new ApiPeriodoAdapter(restTemplate, properties.getConfig());
    }
}
