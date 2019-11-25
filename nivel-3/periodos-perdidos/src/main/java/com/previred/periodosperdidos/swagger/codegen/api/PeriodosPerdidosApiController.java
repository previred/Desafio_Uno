package com.previred.periodosperdidos.swagger.codegen.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodosperdidos.servicio.PeriodosPerdidosService;
import com.previred.periodosperdidos.swagger.codegen.model.PeriodosPerdidos;

@Controller
public class PeriodosPerdidosApiController implements PeriodosPerdidosApi {
	
	@org.springframework.beans.factory.annotation.Autowired
	private PeriodosPerdidosService periodosService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PeriodosPerdidosApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
	public ResponseEntity<PeriodosPerdidos> periodosPerdidosGet() {
		if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                	PeriodosPerdidos periodosPerdidos = periodosService.getPeriodos();	
                    ResponseEntity<PeriodosPerdidos> respuesta = new ResponseEntity<>(periodosPerdidos, HttpStatus.OK);
                    return respuesta;
                } catch (Exception e) {
                	if (e instanceof RestClientException) {
                		log.error("Error en servicio de determinacion de Periodos Faltantes", e);
                		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                	}
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
