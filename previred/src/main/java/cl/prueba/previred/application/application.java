package cl.prueba.previred.application;
import java.util.Arrays;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import cl.prueba.previred.model.GDFechas;


public class application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        GDFechas[] coins = restTemplate.getForObject("--header 'Accept: application/json'http://127.0.0.1:8080/periodos/api", GDFechas[].class);

        // GDD
        Object[] vergeArray = Arrays.stream(coins).toArray();
        log.info("Name :" + ((GDFechas) vergeArray[0]).getId());
        log.info("-------------------------------------------------");
    }
}
