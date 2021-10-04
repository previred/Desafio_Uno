package cl.previred.ms.periodos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableFeignClients
@PropertySources({
        @PropertySource("classpath:rest.properties")
})
public class PeriodosperdidosApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PeriodosperdidosApplication.class, args);
	}

}
