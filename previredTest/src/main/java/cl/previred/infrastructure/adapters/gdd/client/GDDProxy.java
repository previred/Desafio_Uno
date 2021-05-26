package cl.previred.infrastructure.adapters.gdd.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cl.previred.infrastructure.adapters.exception.SourceSystemConstraintViolationsException;
import cl.previred.infrastructure.adapters.exception.SourceSystemServerErrorException;
import cl.previred.infrastructure.adapters.gdd.model.Periodo;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import java.util.Set;

/**
 * @author wmunoz
 */
@Slf4j
@Component
public class GDDProxy {

    private final String system;
    private final RestTemplate restTemplate;
    private final Validator beanValidator;
    private final GDDRestErrorHandler gddRestErrorHandler;

    public GDDProxy(RestTemplate restTemplate,
                      Validator beanValidator,
                      GDDRestErrorHandler gddRestErrorHandler) {
        this.system = "GDD";
        this.restTemplate = restTemplate;
        this.beanValidator = beanValidator;
        this.gddRestErrorHandler = gddRestErrorHandler;
    }
    
   
    @Retryable(value = SourceSystemServerErrorException.class,
            maxAttemptsExpression = "${gdd.retry.max-attempts:3}",
            backoff = @Backoff(delayExpression = "${gdd.retry.delay:10000}"))
    public Periodo get(URI uri, HttpHeaders headers) {

        log.info("action=gddProxyPostStarted");

        Periodo responseBody = null;
        
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Periodo> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Periodo.class);

            responseBody = responseEntity.getBody();

            Set<ConstraintViolation<Object>> violations = beanValidator.validate(responseBody);
            if (!violations.isEmpty()) {
                log.error("action=qrEmvProxyPostInvalidResponse, violations={}", violations);
               throw new SourceSystemConstraintViolationsException(system, violations);
            }
            
            
            
        } catch (HttpClientErrorException e) {
        	gddRestErrorHandler.handleHttpClientErrorException(e, uri.toString());
        } catch (HttpServerErrorException e) {
        	gddRestErrorHandler.handleHttpServerErrorException(e, uri.toString());
        } catch (RestClientException e) {
        	gddRestErrorHandler.handleRestClientException(e);
        }

        return responseBody;
    }

}
