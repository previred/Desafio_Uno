package cl.previred.ms.periodos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.previred.ms.periodos.dtos.MessageDTO;
import cl.previred.ms.periodos.services.utils.HeadersUtils;

@ControllerAdvice
public class ExceptionHandlerController {

    static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<MessageDTO> handleException(final Exception ex) {
        logger.info("handleException - init");
        logger.error("Se ha capturado un error", ex);
        MessageDTO response = handleResponseMessage(ex);
        logger.info("handleException - end");
        return new ResponseEntity<>(response, HeadersUtils.getGenericHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method should handle every kind of exception and map it to an HTTP response
     * @param ex {@link Exception} An exception to be handled
     * @return {@link MessageDTO} A response message mapped from the handled exception
     */
    private MessageDTO handleResponseMessage(final Exception ex) {
    	logger.debug("error Capturado handleResponseMessage",ex);
    	return new MessageDTO("Error Capturado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()); 
    }

}