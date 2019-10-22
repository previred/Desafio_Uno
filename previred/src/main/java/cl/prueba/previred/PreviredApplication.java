package cl.prueba.previred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PreviredApplication {


	public static void main(String[] args) {

		SpringApplication.run(PreviredApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
