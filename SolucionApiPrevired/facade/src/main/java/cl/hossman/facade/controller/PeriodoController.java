package cl.hossman.facade.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.hossman.business.exceptions.BusinessException;
import cl.hossman.business.service.PeriodosService;
import cl.hossman.facade.constants.ApiConstants;
import cl.hossman.entity.model.Periodo;
import cl.hossman.facade.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class PeriodoController implements PeriodosApi {

	@Autowired
	PeriodosService periodoService; 

	@Autowired
	private Environment env;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public PeriodoController(ObjectMapper objectMapper, HttpServletRequest request) {
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
	 * @author Hossman Escobar (H.E)
	 * Metodo REST expuesto
	 *     * 
	 * @throws ApiException */
	@Override
	public ResponseEntity<Periodo> getPeriodos() throws ApiException {	
		if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) { 
				try {
					return new ResponseEntity<Periodo>(periodoService.getPeriodosExternos(objectMapper,env), HttpStatus.OK);	
				} catch (BusinessException e) {
					log.error(e.getMessage(),e);
					throw new ApiException(ApiConstants.CODE_ERR_API_PERIODOS, ApiConstants.MSG_ERR_API_PERIODOS,e);   
				}		
			} else {
				throw new ApiException(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name() , HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()); 
			}
		}
		else {
			log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
		}
		throw new ApiException(HttpStatus.NOT_IMPLEMENTED.name() , HttpStatus.NOT_IMPLEMENTED.getReasonPhrase()); 
	}
}
