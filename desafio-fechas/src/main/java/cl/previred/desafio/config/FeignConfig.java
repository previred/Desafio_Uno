package cl.previred.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.okhttp.OkHttpClient;


@Configuration
public class FeignConfig {
	
	@Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}
