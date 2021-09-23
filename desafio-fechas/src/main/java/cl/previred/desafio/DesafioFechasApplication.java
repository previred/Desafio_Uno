package cl.previred.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioFechasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioFechasApplication.class, args);
	}

}
