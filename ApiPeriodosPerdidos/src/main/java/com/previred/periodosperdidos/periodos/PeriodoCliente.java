package com.previred.periodosperdidos.periodos;

import com.google.gson.Gson;
import com.previred.periodosperdidos.swagger.codegen.api.ApiException;
import com.previred.periodosperdidos.swagger.codegen.model.Periodo;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.logging.Logger;

public class PeriodoCliente extends AbtractPeriodoCliente {
    private static final Logger log = Logger.getLogger(PeriodoCliente.class.getName());
    private static final String URL_ENDPOIND_PERIODOS = "/api";

    public PeriodoCliente(String url, String contextPath) {
        super(url, contextPath);
    }

    public Periodo getPeriodo() throws ApiException, IOException {
        log.info("Inicio Consumo de Endpoint Periodos");
        WebTarget client = createClient(URL_ENDPOIND_PERIODOS);
        Response response = client.request().header("Accept",MediaType.APPLICATION_JSON).get();
        log.info("Status " + response.getStatus());
        Periodo result = null;
        Integer status = response.getStatus();
        if (Response.Status.OK.getStatusCode() == status) {
            result = convertStringToPeriodo(response.readEntity(String.class));

        } else {
            throw new ApiException(status, response.readEntity(String.class));
        }
        return result;
    }

    private Periodo convertStringToPeriodo(String jsonString) {
        if(jsonString!=null){
            Gson g = new Gson();
            return g.fromJson(jsonString, Periodo.class);
        }
        return null;
    }
}
