package com.previred.periodos.determinafaltantes.infrastructure

import com.previred.periodos.determinafaltantes.core.Periodo
import com.previred.periodos.determinafaltantes.core.PeriodoPort
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ApiPeriodoAdapterSpec extends Specification{
    def"retorna un objeto Periodo vacio en caso que la consulta de la API Periodo retorne null"(){
        given:
        RestTemplate restTemplate = Stub(RestTemplate)
        restTemplate.getForObject(_ as String, Periodo.class) >> null
        def config = Stub(ApiPeriodoAdapterConfiguration)
        config.getUrl() >> new URL("http://localhost:8080/periodos/api")
        config.getFormatoFecha() >> ""
        PeriodoPort adapter = new ApiPeriodoAdapter(restTemplate, config)

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        periodo != null
    }

}
