package cl.pabloromero.service.impl

import java.time.LocalDate

import cl.pabloromero.gateway.DesafioUnoGDDGateway
import cl.pabloromero.model.Periodos
import spock.lang.Specification

class DesafioUnoServiceImplSpecs extends Specification{
	
	def service = new DesafioUnoServiceImpl()
	
	def setup() {
		service.gddGateway = Mock(DesafioUnoGDDGateway)
	}
	
	def "Responder fechas desde GDD junto con las fechas faltantes"(){
		given:
			def mockResponse =  Periodos.builder()
											.id(1)
											.fechaCreacion(LocalDate.parse("1969-03-01"))
											.fechaFin(LocalDate.parse("1969-04-01"))
											.fechas([LocalDate.parse("1969-03-01")])
											.build()
			service.gddGateway.periodos() >> mockResponse
		when: "ejecuto al servicio"
			def fechas = service.getFechasFaltantes()
		then: "Obtengo response con las fechas faltantes"
			fechas.id						== mockResponse.id
			fechas.fechaCreacion			== mockResponse.fechaCreacion
			fechas.fechaFin					== mockResponse.fechaFin
			fechas.fechas					== mockResponse.fechas
			fechas.fechasFaltantes.size() > 0
	}
	
	def "Calcular Fechas Faltantes"(){
		given: "Dada la fecha de creacion, la fecha fin y las fechas ya incluidas"
			def fechaCreacion 	= LocalDate.parse("1969-03-01")
			def fechaFin 		= LocalDate.parse("1970-01-01")
			def fechas= [
				LocalDate.parse("1969-03-01"),
				LocalDate.parse("1969-05-01"),
				LocalDate.parse("1969-09-01"),
				LocalDate.parse("1970-01-01")
				]
				
			def fechasFaltantes = [
				LocalDate.parse("1969-04-01"),
				LocalDate.parse("1969-06-01"),
				LocalDate.parse("1969-07-01"),
				LocalDate.parse("1969-08-01"),
				LocalDate.parse("1969-10-01"),
				LocalDate.parse("1969-11-01"),
				LocalDate.parse("1969-12-01")
				]
		when: "calculo las fechas faltantes"
			def fechasFaltantesResponse = service.calcularFechasFaltantes(fechaCreacion, fechaFin, fechas)
		then: "Obtengo fechas faltantes"
			fechasFaltantesResponse == fechasFaltantes
	}
}