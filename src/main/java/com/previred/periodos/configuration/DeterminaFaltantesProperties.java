package com.previred.periodos.configuration;

import com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary.adapter.secundary.ApiPeriodoAdapterConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "determina-faltantes")
@EnableConfigurationProperties
@Getter
@Setter
@Configuration
public class DeterminaFaltantesProperties {
    private ApiPeriodoAdapterConfiguration config;
}
