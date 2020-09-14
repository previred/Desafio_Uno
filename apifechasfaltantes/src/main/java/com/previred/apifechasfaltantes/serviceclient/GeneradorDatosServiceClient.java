package com.previred.apifechasfaltantes.serviceclient;

import com.previred.apifechasfaltantes.config.PropertiesConfig;
import com.previred.apifechasfaltantes.config.PropertiesValue;
import com.previred.apifechasfaltantes.dto.PeriodoDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

public class GeneradorDatosServiceClient {

    public PeriodoDTO getDatos() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);
        PropertiesValue propertiesValue = applicationContext.getBean(PropertiesValue.class);
        ((AnnotationConfigApplicationContext)applicationContext).close();
        return restTemplate.getForObject(propertiesValue.getUrlGeneradorDatos(), PeriodoDTO.class);
    }
}
