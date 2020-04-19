package com.previred.periodos.determinafaltantes.infrastructure

import com.previred.periodos.determinafaltantes.core.Periodo
import com.previred.periodos.determinafaltantes.core.PeriodoPort
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ApiPeriodoAdapterSpec extends Specification{
    @Shared private ApiPeriodoAdapterConfiguration config
    @Shared private RestTemplate restTemplate
    @Shared @Subject private  PeriodoPort adapter

    void setupSpec() {
        config = Stub(ApiPeriodoAdapterConfiguration)
        config.getUrl() >> new URL("http://localhost:8080/periodos/api")
        config.getFormatoFecha() >> "yyyy-MM-dd"
    }

    void setup() {
        restTemplate = Stub(RestTemplate)
        adapter = new ApiPeriodoAdapter(restTemplate, config)
    }

    def"retorna un objeto Periodo vacio en caso que la consulta de la API Periodo retorne null"(){
        given:
        restTemplate.getForObject(_ as String, Periodo.class) >> null

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        periodo != null
    }

    def"retorna un objeto Periodo vacio en caso que se genera un exception al consultar el API Periodo"(){
        given:
        RestTemplate restTemplate = Stub(RestTemplate)
        restTemplate.getForObject(_ as String, Periodo.class) >> new Exception("EXCEPTION")

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        periodo == Periodo.PERIODO_NULO
    }


}
