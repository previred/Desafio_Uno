package com.previred.periodos.apiperiodosperdidos.swagger.codegen.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.apiperiodosperdidos.servicio.PeriodosPerdidoService;
import org.springframework.beans.factory.annotation.Autowired;
import com.previred.periodos.apiperiodosperdidos.swagger.codegen.model.PeriodoPerdido;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ApiApiController implements ApiApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private PeriodosPerdidoService periodosService;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * sobre escribe metodo para la llamada al servicio que le corresponda.
     */
    @Override
    public ResponseEntity<PeriodoPerdido> perdidos() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    PeriodoPerdido detalle = periodosService.getPeriodos();
                    ResponseEntity<PeriodoPerdido> respuesta = new ResponseEntity<>(detalle, HttpStatus.OK);
                    return respuesta;
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/xml", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
