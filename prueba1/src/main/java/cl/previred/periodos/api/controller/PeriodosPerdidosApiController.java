package cl.previred.periodos.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.periodos.api.PeriodosPerdidosApi;
import cl.previred.periodos.dto.PeriodosPerdidosResponseDTO;
import cl.previred.periodos.service.PeriodosPerdidosService;

@RestController
public class PeriodosPerdidosApiController implements PeriodosPerdidosApi {
	
	Logger logger = LoggerFactory.getLogger(PeriodosPerdidosApiController.class);
	
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
	PeriodosPerdidosService periodosPerdidosService;
    
    @Autowired
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
    public ResponseEntity<PeriodosPerdidosResponseDTO> getFechasAleatorias() {
    	
    	logger.info("Llamado a servicio getFechasAleatorias");
    	
		if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                	PeriodosPerdidosResponseDTO response = periodosPerdidosService.getPeriodosPerdidos();
            		return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
        	logger.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    
	
}
