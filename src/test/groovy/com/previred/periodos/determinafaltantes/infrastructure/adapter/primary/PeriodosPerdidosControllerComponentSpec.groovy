package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase
import com.previred.periodos.determinafaltantes.core.PeriodoConFaltantes
import com.previred.periodos.determinafaltantes.core.PeriodoDataSourceNoDisponible
import com.previred.periodos.determinafaltantes.infrastructure.adapter.primary.error.ErrorControllerAdvice
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class PeriodosPerdidosControllerComponentSpec extends Specification {
    @Shared
    private DeterminarPeriodosPerdidosUseCase service
    @Shared
    @Subject
    private PeriodosPerdidosController controller
    @Shared
    private MockMvc mockMvc

    private final def url = "/periodos/perdidos"

    void setup() {
        service = Stub(DeterminarPeriodosPerdidosUseCase)
        controller = new PeriodosPerdidosController(service)
        mockMvc = standaloneSetup(controller).setControllerAdvice(new ErrorControllerAdvice()).build()
    }

    def "Al ocurrir un exception en service, esta es elevada al ControllerAdvice"() {
        given:
        service.calcular() >> { throw new PeriodoDataSourceNoDisponible("ERROR") }
        when:
        def response = this.mockMvc.perform(get(url)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response.andExpect(status().isInternalServerError())
    }


    def "los calculos no son alterados en el controller"() {
        def respuestaService = PeriodoConFaltantes.builder().id(1)
                .fechaCreacion(fechaCreacion)
                .fechaFin(fechaFin)
                .fechas(fechas)
                .fechasFaltantes(faltantes)
                .build()
        given:
        service.calcular() >> respuestaService
        when:
        def response = this.mockMvc.perform(get(url)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        with(response) {
            andExpect(status().isOk())
            andExpect(jsonPath('$.id').value(1))
            andExpect(jsonPath('$.fechaCreacion[0]').value(fechaCreacion.getYear()))
            andExpect(jsonPath('$.fechaCreacion[1]').value(fechaCreacion.getMonthValue()))
            andExpect(jsonPath('$.fechaCreacion[2]').value(fechaCreacion.getDayOfMonth()))
            andExpect(jsonPath('$.fechaFin[0]').value(fechaFin.getYear()))
            andExpect(jsonPath('$.fechaFin[1]').value(fechaFin.getMonthValue()))
            andExpect(jsonPath('$.fechaFin[2]').value(fechaFin.getDayOfMonth()))
            andExpect(jsonPath('$.fechas', hasSize(fechas.size())))
            andExpect(jsonPath('$.fechasFaltantes', hasSize(faltantes.size())))
        }
        where:
        fechaCreacion            | fechaFin                 | fechas                                                                                                                                  | faltantes
        LocalDate.of(2020, 2, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [] as TreeSet
        LocalDate.of(2020, 1, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [LocalDate.parse("2020-02-01")] as TreeSet
        LocalDate.of(1969, 3, 1) | LocalDate.of(1970, 1, 1) | [LocalDate.parse("1969-03-01"), LocalDate.parse("1969-05-01"), LocalDate.parse("1969-09-01"), LocalDate.parse("1970-01-01")] as TreeSet | [LocalDate.parse("1969-04-01"), LocalDate.parse("1969-06-01"), LocalDate.parse("1969-07-01"), LocalDate.parse("1969-08-01"), LocalDate.parse("1969-10-01"), LocalDate.parse("1969-11-01"), LocalDate.parse("1969-12-01")] as TreeSet
    }
}
