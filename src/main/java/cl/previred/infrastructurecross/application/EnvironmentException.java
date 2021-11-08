package cl.previred.infrastructurecross.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentException {
	
	@Autowired
	private MessageApplicationConfig messageApplicationConfig;
	
	public void error(Integer applicationCode){
		throw new PeriodosPerdidosException(
				messageApplicationConfig.getMessageByAppCode(applicationCode), 
				applicationCode, 
				messageApplicationConfig.getHttpCodeByAppCode(applicationCode)); 			
	}
	
	public void error(String message, Integer applicationCode){
		throw new PeriodosPerdidosException(
				message, 
				applicationCode, 
				messageApplicationConfig.getHttpCodeByAppCode(applicationCode)); 			
	}
	
	public void error(String message){
		throw new PeriodosPerdidosException(message, 500); 			
	}
}
