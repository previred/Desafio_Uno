package cl.previred.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.domain.port.GDDBackend;


/**
 * @author wmunoz
 */
@Service
public class DateManagementService{
	
	@Autowired
    private GDDBackend gddBackend;
	   
    public PeriodoResult getGDDFilteredResponse() throws InternalBussinesException {
    	
    	PeriodoResult periodoResult = gddBackend.getRandomData();
    
    	return periodoResult;
       
    }
    
    
   
    
    
}
