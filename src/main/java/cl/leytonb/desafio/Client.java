package cl.leytonb.desafio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

public final class Client {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final JavaType TYPE = MAPPER.getTypeFactory().constructType(GeneradorResp.class);
	private String url = "http://127.0.0.1:8080/periodos/api";

	{
		// do it one time
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_DATE_TIME));
		MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		MAPPER.registerModule(javaTimeModule);
	}

	public Client(String[] args) {
		if (args != null && args.length >= 1) {
			String u = args[0];
			if (u != null && u.startsWith("http")) {
				url = u;
			}
		}
	}

	public final GeneradorResp getDates() throws ClientException {
		try {
			GetRequest request = Unirest.get(url);
			request.getHeaders().put("Accept", Arrays.asList("application/json"));
			return getResponse(request);
		} catch (UnirestException | IOException e) {
			throw new ClientException(e);
		}
	}

	private GeneradorResp getResponse(GetRequest request) throws UnirestException, IOException {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(request.asBinary().getBody(), StandardCharsets.UTF_8))) {
			return MAPPER.readValue(br, TYPE);
		}
	}

}
