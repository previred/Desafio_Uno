package cl.cmoscoso.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Configuration
public class BeansCatalog {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper om = new ObjectMapper();
		JavaTimeModule module = new JavaTimeModule();

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

		LocalDateDeserializer dateTimeDeserializer = new LocalDateDeserializer(formatter);
		LocalDateSerializer dateTimeSerializer = new LocalDateSerializer(formatter);

		module.addSerializer(LocalDate.class, dateTimeSerializer);
		module.addDeserializer(LocalDate.class, dateTimeDeserializer);

		om.registerModule(module);
		return om;
	}

}
