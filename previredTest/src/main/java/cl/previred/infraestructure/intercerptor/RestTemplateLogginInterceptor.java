package cl.previred.infraestructure.intercerptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * @author wmunoz
 * @since 1.0
 * @version 1.0
 */
@Component
@Slf4j
public class RestTemplateLogginInterceptor implements ClientHttpRequestInterceptor {


	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		long start_time, end_time;

		log.debug(String.format("URI [%s]", request.getURI().toString()));
		log.debug(String.format("metod [%s]", request.getMethod().name()));
		log.debug(String.format("headers [%s]", request.getHeaders().toString()));
		log.debug(String.format("REQ body  [%s]", new String(body)));

		start_time = System.nanoTime();
		ClientHttpResponse response = execution.execute(request, body);
		end_time = System.nanoTime();

		log.debug(String.format("RES status code [%s]", response.getStatusCode().toString()));
		log.debug(String.format("RES headers [%s]", response.getHeaders().toString()));



		log.info(String.format("responseTime=%.3f [ms]f", (end_time - start_time) / 1e6));
		return response;
	}
}