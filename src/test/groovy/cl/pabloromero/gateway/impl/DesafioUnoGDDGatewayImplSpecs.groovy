package cl.pabloromero.gateway.impl

import java.time.LocalDate

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import cl.pabloromero.model.Periodos
import spock.lang.Specification

class DesafioUnoGDDGatewayImplSpecs extends Specification{
	
	def gateway = new DesafioUnoGDDGatewayImpl()
	
	def setup() {
		gateway.restTemplate = Mock(RestTemplate)
	}
	
	def "Responder fechas desde GDD junto con las fechas faltantes"(){
		given:
			def mockResponse = Periodos.builder()
								.id(1)
								.fechaCreacion(LocalDate.parse("1969-03-01"))
								.fechaFin(LocalDate.parse("1969-04-01"))
								.fechas([LocalDate.parse("1969-03-01")])
								.build()
			gateway.restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(mockResponse)
		when: "ejecuto al servicio"
			def fechas = gateway.periodos()
		then: "Obtengo response con las fechas faltantes"
			fechas.id						== mockResponse.id
			fechas.fechaCreacion			== mockResponse.fechaCreacion
			fechas.fechaFin					== mockResponse.fechaFin
			fechas.fechas					== mockResponse.fechas
	}
	
	def "Obtener instancia RestTemplate"(){
		when: "ejecuto el metodo"
			def restTemplate = gateway.restTemplate()
		then: 
			restTemplate
	}
	
}