package cl.previred.desafio.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PreviredDesafioUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreviredDesafioUnoApplication.class, args);
	}

}
