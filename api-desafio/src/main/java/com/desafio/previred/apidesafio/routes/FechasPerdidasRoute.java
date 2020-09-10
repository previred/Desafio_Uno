package com.desafio.previred.apidesafio.routes;

import java.net.ConnectException;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.desafio.previred.apidesafio.RestConfig;
import com.desafio.previred.apidesafio.fechasfaltantes.FechasFaltantesService;
import com.desafio.previred.apidesafio.generador.FechasFaltantesRequest;

@Component
public class FechasPerdidasRoute extends RouteBuilder{

	@Value("${com.ruta.servicio.periodos}")
	private String rutaServicioPeriodos;
	
	public static final String RUTA_CONSULTA = "direct:consultaRest";
	
	@Override
	public void configure() throws Exception {
		RestConfig.inicializarRest(this);
		
		onException(ConnectException.class)
		.handled(true)
		.maximumRedeliveries(0)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(503))
		.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN_VALUE))
		.setBody().simple("El servicio externo " +rutaServicioPeriodos +" no responde, Error :${exception.message}")
		.log("${exception.stacktrace}")
		.end();
		
		onException(Exception.class)
			.handled(true)
			.maximumRedeliveries(0)
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN_VALUE))
			.setBody().simple("Se ha generado un error :${exception.message}")
			.log("${exception.stacktrace}")
			.end();
		
		
		rest("/periodosperdidos").tag("Periodos perdidos")
				.get().id("rest-fechasfaltantes")
				.description("Servicio REST que busca las fechas no informadas por el servicio Generador de datos")
				.to(RUTA_CONSULTA);
		
		from(RUTA_CONSULTA)
				.log("Comienza la llamada al servicio externo Generador de datos de periodos")
				.removeHeaders("CamelHttp*")
				.streamCaching()
				.marshal().json(JsonLibrary.Jackson, false)
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to(rutaServicioPeriodos)
				.log("Respuesta del servicio externo: ${body}")
				.unmarshal().json(JsonLibrary.Jackson, FechasFaltantesRequest.class)
				.bean(FechasFaltantesService.class, "buscaFechasFaltantes(${body})")
				.endRest();
		
	}

}
