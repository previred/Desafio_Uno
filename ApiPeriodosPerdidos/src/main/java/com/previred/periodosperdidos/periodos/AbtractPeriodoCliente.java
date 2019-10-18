package com.previred.periodosperdidos.periodos;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Logger;
public class AbtractPeriodoCliente {
    private String url;
    private String contextPath;

    private static final Logger log = Logger.getLogger(AbtractPeriodoCliente.class.getName());

    public AbtractPeriodoCliente(String url, String contextPath) {
        this.url = url;
        this.contextPath = contextPath;
    }

    protected WebTarget createClient(String path) {
        String assembledPath = assembleEndpoint(path);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assembledPath);
        return target;
    }

    private String assembleEndpoint(String path) {
        String endpoint = url.concat(contextPath).concat(path);
        log.info(String.format("Calling endpoint %s", endpoint));
        return endpoint;
    }
}
