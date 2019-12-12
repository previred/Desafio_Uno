package cl.julillo.datesrecovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DatesRecoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatesRecoveryApplication.class, args);
	}

}
