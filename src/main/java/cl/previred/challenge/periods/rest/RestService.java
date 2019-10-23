package cl.previred.challenge.periods.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
public abstract class RestService<Q> {

    private String uri;

    private RestTemplate restTemplate;

    public RestService(){
        restTemplate = new RestTemplate();
    }

    protected abstract Class<Q> type();

    public Q get(HttpHeaders headers) {
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Q> response = restTemplate.exchange(getUri(), HttpMethod.GET, entity, type());
        return response.getBody();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
