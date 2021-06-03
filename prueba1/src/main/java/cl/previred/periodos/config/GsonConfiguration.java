package cl.previred.periodos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class GsonConfiguration {
	@Bean
	public Gson gson() {
		return new Gson();
	}
}
