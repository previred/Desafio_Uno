package cl.previred.challenge.periods.service;

import cl.previred.challenge.periods.rest.gdd.GddResponse;
import cl.previred.challenge.periods.rest.gdd.GddRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.y.z - 22-10-2019
 * @since 1.y.z - 22-10-2019
 */
@Service
public class GddService {

    @Autowired
    private GddRestService gddRestService;

    public GddResponse getGdd(){
        GddResponse response;

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        response = gddRestService.get(headers);

        return response;
    }
}
