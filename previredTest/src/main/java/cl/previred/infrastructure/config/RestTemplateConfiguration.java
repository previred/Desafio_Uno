/**
 *
 */
package cl.previred.infrastructure.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import cl.previred.infraestructure.intercerptor.RestTemplateLogginInterceptor;

/**
 * @author wmunoz
 * @since 1.0
 * @version 1.0
 */
@Configuration
public class RestTemplateConfiguration {

	@Value("${datemanagement.connection.request.timeout}")
	private Integer connectionRequestTimeout;

	@Value("${datemanagement.connection.timeout}")
	private Integer connectionTimeout;

	@Value("${datemanagement.connection.socket.timeout}")
	private Integer readTimeout;


	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();

		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
		httpComponentsClientHttpRequestFactory.setConnectTimeout(connectionTimeout);
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLogginInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
