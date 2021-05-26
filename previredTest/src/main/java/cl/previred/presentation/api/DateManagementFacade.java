package cl.previred.presentation.api;

import org.springframework.stereotype.Component;

import cl.previred.domain.DateManagementService;
import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.presentation.api.model.PeriodoResponse;

/**
 * @author wmunoz
 */
@Component
public class DateManagementFacade {

    private final DateManagementService dateManagementService;

    public DateManagementFacade(DateManagementService dateManagementService) {
        this.dateManagementService = dateManagementService;
    }

    public PeriodoResponse filtered() throws InternalBussinesException {

    	PeriodoResult periodoResult = dateManagementService.getGDDFilteredResponse();

        return PeriodoResponse.of(periodoResult);
    }
}
