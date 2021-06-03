package cl.previred.periodos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PeriodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeriodosApplication.class, args);
	}

}
