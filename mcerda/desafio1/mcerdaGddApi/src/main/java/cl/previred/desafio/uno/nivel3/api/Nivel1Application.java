package cl.previred.desafio.uno.nivel3.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class Nivel1Application {
	
	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(Nivel1Application.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
		
	}

	
}
