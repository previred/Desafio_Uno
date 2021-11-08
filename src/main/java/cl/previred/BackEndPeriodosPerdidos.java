package cl.previred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ 
	"cl.previred",
	"cl.previred.application",  
	"cl.previred.infrastructurecross" })
@SpringBootApplication
@EnableCaching
public class BackEndPeriodosPerdidos {
	public static void main(String[] args) {
		SpringApplication.run(BackEndPeriodosPerdidos.class, args);
	}
}
