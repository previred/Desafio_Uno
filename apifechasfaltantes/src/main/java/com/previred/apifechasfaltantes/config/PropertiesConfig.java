package com.previred.apifechasfaltantes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
    @Autowired
    Environment env;

    @Bean
    public PropertiesValue propertiesValue(){
        PropertiesValue propertiesValue = new PropertiesValue();
        propertiesValue.setUrlGeneradorDatos(env.getProperty("url.generadorDatos"));
        return propertiesValue;
    }
}
