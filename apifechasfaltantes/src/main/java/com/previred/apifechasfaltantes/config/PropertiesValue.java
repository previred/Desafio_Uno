package com.previred.apifechasfaltantes.config;

import org.springframework.stereotype.Component;

@Component
public class PropertiesValue {

    private String urlGeneradorDatos;

    public String getUrlGeneradorDatos() {
        return urlGeneradorDatos;
    }

    public void setUrlGeneradorDatos(String urlGeneradorDatos) {
        this.urlGeneradorDatos = urlGeneradorDatos;
    }

}
