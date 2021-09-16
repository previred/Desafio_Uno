package cl.previred.prueba.tecnica.call;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author jgajardo
 *
 *
 */
@Setter
public class AbstractGenericCallService<T> {

	@Autowired
	RestTemplate restTemplateWithErrorHandler;

	public static final String EMPTY_STRING = "";
	protected HttpHeaders headers = new HttpHeaders();
	protected StringBuilder invokeData;
	protected String body;
	protected HttpMethod currentMethod;
	protected String protocolo;
	protected String host;
	protected String port;
	protected String path;

	protected AbstractGenericCallService(String protocolo, String host, String port, String path) {
		this.protocolo = protocolo;
		this.host = host;
		this.port = port;
		this.path = path;
	}

	protected <C> C invoke(Class<C> classType) throws IOException {
		ResponseEntity<String> stringResponseEntity = callRestService();
		return responseToObject(stringResponseEntity.getBody(), classType);
	}

	protected ResponseEntity<String> invokeComplete() {
		return callRestService();
	}

	private static <C> C responseToObject(String bodyResponse, Class<C> classType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.readValue(bodyResponse, classType);
	}

	protected ResponseEntity<String> callRestService() {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance().uri(getUri());

		return restTemplateWithErrorHandler.exchange(createUri(builder), getHttpMethod(),
				createHttpEntity(getJsonPayload()), new ParameterizedTypeReference<String>() {
				});
	}

	private URI getUri() {
		return URI.create(getStringUri());
	}

	private static URI createUri(UriComponentsBuilder builder) {
		return builder.build().encode().toUri();
	}

	private HttpEntity<String> createHttpEntity(String rq) {
		headers.clear();
		headers.setContentType(MediaType.APPLICATION_JSON);
		addHeaderParms();
		return new HttpEntity<>((null != rq ? rq : ""), headers);
	}

	protected List<T> invokeGetAll() throws IOException {
		ResponseEntity<String> stringResponseEntity = callRestService();
		return responseListToObject(stringResponseEntity.getBody());
	}

	private List<T> responseListToObject(String bodyResponse) throws IOException {

		return new ObjectMapper().readValue(bodyResponse, new TypeReference<List<T>>() {
		});
	}

	protected String getStringUri() {
		return this.invokeData.toString();
	}

	protected String getJsonPayload() {
		return body;
	}

	protected void addHeaderParms() {
		// Not needed
	}

	protected HttpMethod getHttpMethod() {
		return this.currentMethod;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
