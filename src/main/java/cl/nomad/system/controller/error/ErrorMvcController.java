package cl.nomad.system.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class ErrorMvcController {
    private org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());

    @ExceptionHandler(Exception.class)
    public String addError(Exception exception, ServletWebRequest webRequest, HttpServletRequest request){
    	//no handler 
        return "error/500";
    }
}
