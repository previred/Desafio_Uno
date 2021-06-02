package cl.previred.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = "cl.previred.test")
//Por si deseamos que las proerties esten externas o internas
//@PropertySource("file:/path/to/properties/info_periodos.properties")
@PropertySource("classpath:info_periodos.properties")
public class PeriodosApplication {

	private final Logger LOG = LogManager.getLogger(this.getClass());
	
	public static void main(String args) {
		SpringApplication.run(PeriodosApplication.class, args);

	}

	@Bean
	ApplicationRunner applicationRunner(Environment env) {
		return args -> {
			LOG.info(env.getProperty("saludo.inicio"));
		};
	}
	
	
}
