package cl.previred.infrastructure.adapters.gdd.client;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.infrastructure.adapters.gdd.model.Periodo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * @author wmunoz
 */
@Component
public class GDDClient {

    private final GDDUriBuilder gddUriBuilder;
    private final GDDProxy gddProxy;
    private final GDDHeadersBuilder gddHeadersBuilder;
    private final GDDUtil gddUtil;


    public GDDClient(GDDUriBuilder gddUriBuilder,GDDHeadersBuilder gddHeadersBuilder,GDDProxy gddProxy,GDDUtil gddUtil) {
        this.gddUriBuilder = gddUriBuilder;
        this.gddHeadersBuilder = gddHeadersBuilder;
        this.gddProxy = gddProxy;
        this.gddUtil = gddUtil;
    }

    public PeriodoResult getRandomData() throws InternalBussinesException {
        URI uri = gddUriBuilder.build();
        HttpHeaders headers = gddHeadersBuilder.getHttpHeaders();
        Periodo periodo = gddProxy.get(uri,headers);       
        List<LocalDate> theMissingDates = gddUtil.giveMeTheMissingDates(periodo);
        
        return periodo.toDomain(theMissingDates);
    }
    
}
