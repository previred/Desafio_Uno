package cl.nomad.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import cl.nomad.service.ResultadoService;
import cl.nomad.system.data.entity.Resultado;

@SuppressWarnings("deprecation") //no JavaTimeModule por jackson 
@Service
public class ResultadoServiceImpl implements ResultadoService {
	
	@Value("${desafio.periodos.server}")
	private String server;
	
	@Value("${desafio.periodos.rest.uri}")
	private String uri;

	private RestTemplate rest;
	private HttpHeaders headers;

	public ResultadoServiceImpl() {
		
		this.rest = new RestTemplate();
		
		this.headers = new HttpHeaders();
		headers.add("Accept", "application/json");
	}

	@Override
	public String buscarDesafio() throws JsonProcessingException {
		
		Resultado res;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JSR310Module());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		res = this.calcularFaltantes(this.buscarBody());

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
	}
	
	@Override
	public Resultado buscarJson() {
		return this.calcularFaltantes(this.buscarBody());
	}
	
	private Resultado buscarBody() {
		
		System.out.println("Consultando Api Periodos" + new Date());
		
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<Resultado> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity,
				Resultado.class);
		
		System.out.println("Respuesta Api Periodos" + new Date());
		
		// TODO: id llega como 1 del runtime, guardar variable incremental en nuestro runtime?.
		
		return responseEntity.getBody();
	}

	private Resultado calcularFaltantes(Resultado res) {
		
		List<LocalDate> estan = new ArrayList<LocalDate>();
		List<LocalDate> faltantes = new ArrayList<LocalDate>();
		
		List<LocalDate> fechasEntregadas = res.getFechas();
		Collections.sort(fechasEntregadas, Collections.reverseOrder());
		
		List<LocalDate> todas = buscarFechasEntremedio(res.getFechaCreacion(), res.getFechaFin());
		Collections.sort(todas, Collections.reverseOrder());
		
		for (LocalDate deberia : todas) {
			
			for (LocalDate recibida : fechasEntregadas) {
				
				if (recibida.equals(deberia)) {
					estan.add(deberia);
				}

			}
			
		};		
		
		faltantes = todas;
		faltantes.removeAll(estan);
		
		faltantes.add(0, res.getFechaFin());
		
		res.setFechasFaltantes(faltantes);
		
		System.out.println("Calculo realizado " + new Date());
		
		return res;
	}



	private List<LocalDate> buscarFechasEntremedio(LocalDate inicio, LocalDate fin) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(inicio, fin);
		
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> inicio.plusDays(i))
				.collect(Collectors.toList());
	}


}
