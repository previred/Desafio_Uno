package com.previred.periodos.swagger.codegen.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.servicio.PeriodosService;
import com.previred.periodos.swagger.codegen.model.Periodo;
import java.util.List;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ApiApiController implements ApiApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PeriodosService periodosService;

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

    @Override
    public ResponseEntity<Periodo> periodos() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    Periodo detalle = periodosService.getPeriodos();
                    ResponseEntity<Periodo> respuesta = new ResponseEntity<>(detalle, HttpStatus.OK);
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
