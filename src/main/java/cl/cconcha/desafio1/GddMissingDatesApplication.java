package cl.cconcha.desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GddMissingDatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GddMissingDatesApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplateInstance() {
		return new RestTemplate();
	}

}
