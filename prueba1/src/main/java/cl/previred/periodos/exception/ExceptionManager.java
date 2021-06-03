package cl.previred.periodos.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import cl.previred.periodos.dao.impl.PeriodosPerdidosDAOImpl;
import cl.previred.periodos.exception.response.ErrorResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionManager
{
	public static Logger logger = LoggerFactory.getLogger(PeriodosPerdidosDAOImpl.class);
	
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    	logger.info("Excepcion inesperada capturada en manejador de Excepciones", ex);
    	List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("UNKNOWN-SERVER-ERROR", details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(PreviredTechnicalException.class)
    public final ResponseEntity<Object> handlePreviredTechnicalException(PreviredTechnicalException ex, WebRequest request) {
    	logger.info("Excepcion tecnica capturada en manejador de excepciones", ex);
    	List<String> details = new ArrayList<>();
        details.add("Detalles: ".concat(ex.getMensaje()));
        ErrorResponse error = new ErrorResponse(ex.getCodigo(), details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //En el pasado he tenido conflictos con RestTemplate al utilizar un Status Code Custom
    //Se reutilizara un codigo no utilizado en el estándar para dicho fin
    @ExceptionHandler(PreviredBusinessException.class)
    public final ResponseEntity<Object> handlePreviredBusinessException(PreviredBusinessException ex, WebRequest request) {
    	logger.info("Excepcion de negocio capturada en manejador de excepciones", ex);
    	List<String> details = new ArrayList<>();
        details.add("Detalles: ".concat(ex.getMensaje()));
        ErrorResponse error = new ErrorResponse(ex.getCodigo(),details);
        return new ResponseEntity<Object>(error, HttpStatus.I_AM_A_TEAPOT);
    }
    
}
