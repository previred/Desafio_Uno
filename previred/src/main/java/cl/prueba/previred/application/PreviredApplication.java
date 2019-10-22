package cl.prueba.previred.application;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import cl.prueba.previred.model.GDFechas;

@SpringBootApplication
public class PreviredApplication {
    private static final Logger log = LoggerFactory.getLogger(PreviredApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PreviredApplication.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            GDFechas[] coins = restTemplate.getForObject("--header 'Accept: application/json'http://127.0.0.1:8080/periodos/api", GDFechas[].class);
            // GDD
            Object[] vergeArray = Arrays.stream(coins).toArray();
            log.info("Name :" + ((GDFechas) vergeArray[0]).getId());
            log.info("-------------------------------------------------");
        };
    }

}
