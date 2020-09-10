package com.desafio.previred.apidesafio;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestConfig {

    private RestConfig() {
    }

    public static void inicializarRest(RouteBuilder route) {
        route.restConfiguration()
            .consumerProperty("bridgeErrorHandler", "true")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .contextPath("/api")
            .apiContextPath("/swagger") 
            .apiContextRouteId("swagger")

            .apiProperty("api.title", "Periodos Perdidos")
            .apiProperty("api.version", "1.0")
            .apiProperty("api.contact.name", "Guillermo Cuevas Flores")
            .apiProperty("api.contact.email", "g.cuevas852@gmail.com")
            .apiProperty("host", "")
            .apiProperty("port", "9091")
            ;
    }
}
