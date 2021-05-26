package cl.previred.presentation.api;

import cl.previred.domain.DateManagementService;
import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.presentation.api.model.PeriodoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DateManagementFacadeTest {

    @Mock
    private DateManagementService dateManagementService;

    @InjectMocks
    private DateManagementFacade dateManagementFacade;

    @Test
    void shouldFiltered() throws InternalBussinesException {

    	List<LocalDate> lr = new ArrayList<LocalDate>();
    	lr.add(LocalDate.parse("2020-01-08"));

        PeriodoResult periodoResult = PeriodoResult
    			.builder()
    			.id(1l)
    			.fechaCreacion(LocalDate.parse("2020-01-08"))
    			.fechaFin(LocalDate.parse("2020-01-08"))
    			.fechasRec(lr)
    			.fechasFal(lr)
    			.build();
    	

        given(dateManagementService.getGDDFilteredResponse()).willReturn(periodoResult);

        PeriodoResponse validationResponse = dateManagementFacade.filtered();

        assertThat(validationResponse).isNotNull();
        assertThat(validationResponse.getFechasRec()).isNotNull().containsExactlyInAnyOrderElementsOf(periodoResult.getFechasRec());
        assertThat(validationResponse.getFechaFin()).isNotNull() .isEqualTo(periodoResult.getFechaFin());
    }

}