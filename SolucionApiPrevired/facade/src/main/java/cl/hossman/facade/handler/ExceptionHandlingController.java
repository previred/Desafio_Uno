package cl.hossman.facade.handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import cl.hossman.facade.exceptions.ApiException;
import cl.hossman.facade.constants.ApiConstants;
import cl.hossman.facade.responses.ApiResponseMessageError;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request){
		return new ResponseEntity<>( new ApiResponseMessageError(ex.getCode(),ex.getMessage(),ApiConstants.TYPE_ERROR), httpErrorBuilder(ex));
		
	}
	
	private HttpStatus httpErrorBuilder(ApiException ex){
		if(ex.getCode().equals(ApiConstants.CODE_ERR_API_PERIODOS)) {
			return HttpStatus.BAD_GATEWAY;
		} else if(ex.getCode().equals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())) {
			return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		} 	
		return HttpStatus.NOT_IMPLEMENTED;
	} 
 
}
