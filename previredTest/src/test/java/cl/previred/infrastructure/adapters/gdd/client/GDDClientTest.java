package cl.previred.infrastructure.adapters.gdd.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.infrastructure.adapters.gdd.model.Periodo;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class GDDClientTest {

    @Mock
    private GDDUriBuilder gddUriBuilder;
    @Mock
    private GDDHeadersBuilder gddHeadersBuilder;
    @Mock
    private GDDProxy gddProxy;
    @Mock
    private GDDUtil gddUtil;

    @InjectMocks
    private GDDClient gddClient;
    

    @Test
    void shouldGetPeriod() throws InternalBussinesException {


        URI uri = URI.create("http://mock/get");
        given(gddUriBuilder.build()).willReturn(uri);

        HttpHeaders headers = HttpHeaders.EMPTY;
        given(gddHeadersBuilder.getHttpHeaders()).willReturn(headers);
        
        List<LocalDate> missingDates = new ArrayList<LocalDate>();
        missingDates.add(LocalDate.parse("2020-01-08"));
        
        
        List<LocalDate> lr = new ArrayList<LocalDate>();
    	lr.add(LocalDate.parse("2020-01-08"));

             
        Periodo periodo = Periodo
      		.builder()
      		.id(1L)
      		.fechaCreacion(LocalDate.parse("2020-01-08"))
      		.fechaFin(LocalDate.parse("2020-01-08"))
      		.fechas(lr)
      		.build();
        given(gddProxy.get(eq(uri), eq(headers))).willReturn(periodo);

        
        given(gddUtil.giveMeTheMissingDates(eq(periodo))).willReturn(missingDates);


        PeriodoResult qrResponse = gddClient.getRandomData();

        assertThat(qrResponse.getId()).isEqualTo(1L);

        
    }
}