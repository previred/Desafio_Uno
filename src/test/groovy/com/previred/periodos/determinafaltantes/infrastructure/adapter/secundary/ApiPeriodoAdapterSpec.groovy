package com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary

import com.previred.periodos.determinafaltantes.core.Periodo
import com.previred.periodos.determinafaltantes.core.PeriodoPort
import com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary.adapter.secundary.ApiPeriodoAdapter
import com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary.adapter.secundary.ApiPeriodoAdapterConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class ApiPeriodoAdapterSpec extends Specification {
    @Shared
    private ApiPeriodoAdapterConfiguration config
    @Shared
    private RestTemplate restTemplate
    @Shared
    @Subject
    private PeriodoPort adapter

    void setupSpec() {
        config = Stub(ApiPeriodoAdapterConfiguration)
        config.getUrl() >> new URL("http://localhost:8080/periodos/api")
        config.getFormatoFecha() >> "yyyy-MM-dd"
        config.getFechaPorDefecto() >> LocalDate.of(0, 1, 1)
    }

    void setup() {
        restTemplate = Stub(RestTemplate)
        adapter = new ApiPeriodoAdapter(restTemplate, config)
    }

    def "retorna un objeto Periodo vacio en caso que la consulta de la API Periodo retorne null"() {
        given:
        restTemplate.getForObject(_ as String, Periodo.class) >> null

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        periodo != null
    }

    def "retorna un objeto Periodo vacio en caso que se genera un exception al consultar el API Periodo"() {
        given:
        restTemplate.getForObject(_ as String, Periodo.class) >> {throw new Exception("EXCEPTION")}

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        periodo == Periodo.PERIODO_NULO
    }

    def "retorna la lista de fechas y el periodo al cual corresponden"() {
        def respuesta = Periodo.builder().id(1).fechaCreacion(LocalDate.of(1968,8,1))
                .fechaFin(LocalDate.of(1971,6,1))
                .fechas([LocalDate.parse("1969-03-01"),LocalDate.parse("1969-05-01")
                         ,LocalDate.parse("1969-09-01"),LocalDate.parse("1971-05-01")] as TreeSet)
                .build()
        given:
        restTemplate.getForObject(_ as String, Periodo.class) >> respuesta

        when:
        Periodo periodo = adapter.getFechasPeriodoAleatorio()

        then:
        with(periodo) {
            getId() == respuesta.getId()
            getFechaCreacion() == respuesta.getFechaCreacion()
            getFechaFin() == respuesta.getFechaFin()
            getFechas().containsAll(respuesta.getFechas())
        }
    }


}
