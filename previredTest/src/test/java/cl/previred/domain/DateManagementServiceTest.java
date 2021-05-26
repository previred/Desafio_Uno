package cl.previred.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.domain.port.GDDBackend;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class DateManagementServiceTest {
				
    @Mock
    private GDDBackend gddBackend;
	
    @InjectMocks
    private DateManagementService dateManagementService;

    @Test
    void shouldFiltered() throws InternalBussinesException {
            
        List<LocalDate> ld = new ArrayList<LocalDate>();
        ld.add(LocalDate.parse("2020-01-08"));
                 
    	PeriodoResult periodoResult = PeriodoResult
      		.builder()
      		.id(1L)
      		.fechaCreacion(LocalDate.parse("2020-01-08"))
      		.fechaFin(LocalDate.parse("2020-01-08"))
            .fechasRec(ld)
            .fechasFal(ld)
      		.build();

        given(gddBackend.getRandomData()).willReturn(periodoResult);

        PeriodoResult expectedPeriodoResult = dateManagementService.getGDDFilteredResponse();

        assertThat(expectedPeriodoResult).isNotNull().isEqualTo(periodoResult);

    }
	    	

}


