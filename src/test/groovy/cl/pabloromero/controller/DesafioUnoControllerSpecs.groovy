package cl.pabloromero.controller

import org.mockito.Mock

import cl.pabloromero.model.FechasFaltantesResponse
import cl.pabloromero.service.DesafioUnoService
import spock.lang.Specification

class DesafioUnoControllerSpecs extends Specification{
	
	def controller = new DesafioUnoController()
	
	def setup() {
		controller.service = Mock(DesafioUnoService)
	}
	
	def "Responder fechas desde GDD junto con las fechas faltantes"(){
		given:
			controller.service.getFechasFaltantes() >> FechasFaltantesResponse.builder()
														.id(1)
														.fechaCreacion(new Date())
														.fechaFin(new Date())
														.fechas([new Date()])
														.fechasFaltantes([new Date()])
														.build()
		when: "ejecuto al servicio"
			def response = controller.getFechasFaltantes()
		then:
			response.id
			response.fechaCreacion
			response.fechaFin
			response.fechas.size() > 0
			response.fechasFaltantes.size() > 0
	}
}