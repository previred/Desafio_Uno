package cl.pabloromero.service.impl

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
											.fechaCreacion(new Date())
											.fechaFin(new Date())
											.fechas([new Date()])
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
			def fechaCreacion 	= (new Date()).copyWith(year:1969, month: Calendar.MARCH, 	dayOfMonth:01)
			def fechaFin 		= Date.copyWith(year:1970, month: Calendar.JANUARY, dayOfMonth:01)
			def fechas= [
				Date.copyWith(year:1969, month: Calendar.MARCH, 	dayOfMonth:01),
				Date.copyWith(year:1969, month: Calendar.MAY, 		dayOfMonth:01),
				Date.copyWith(year:1969, month: Calendar.SEPTEMBER, dayOfMonth:01),
				Date.copyWith(year:1970, month: Calendar.JANUARY,	dayOfMonth:01),
				]
				
			def fechasFaltantes = [
				Date.parse("yyyy-MM-dd", "1969-04-01"),
				Date.parse("yyyy-MM-dd", "1969-06-01"),
				Date.parse("yyyy-MM-dd", "1969-07-01"),
				Date.parse("yyyy-MM-dd", "1969-08-01"),
				Date.parse("yyyy-MM-dd", "1969-10-01"),
				Date.parse("yyyy-MM-dd", "1969-11-01"),
				Date.parse("yyyy-MM-dd", "1969-12-01")
				]
			service.gddGateway.periodos() >> mockResponse
		when: "calculo las fechas faltantes"
			def fechasFaltantesResponse = service.calcularFechasFaltantes(fechaCreacion, fechaCreacion, fechas)
		then: "Obtengo fechas faltantes"
			fechasFaltantesResponse == fechasFaltantes
	}
}