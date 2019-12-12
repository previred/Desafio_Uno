package cl.julillo.datesrecovery.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean("gddRestTemplate")
	public RestTemplate getGddRestTemplate() {
		RestTemplate rt = new RestTemplate();
		MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter();
		c.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
		rt.getMessageConverters().add(c);
		return rt;
	}
}
