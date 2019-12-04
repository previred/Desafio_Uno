package cl.previred.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration()
@ComponentScan(basePackages = "cl.previred.app")
public class DesafioUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioUnoApplication.class, args);
	}

}
