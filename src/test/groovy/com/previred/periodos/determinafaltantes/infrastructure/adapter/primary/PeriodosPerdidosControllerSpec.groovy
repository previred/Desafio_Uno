package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase
import com.previred.periodos.determinafaltantes.core.PeriodoConFaltantes
import com.previred.periodos.determinafaltantes.core.PeriodoDataSourceNoDisponible
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class PeriodosPerdidosControllerSpec extends Specification {
    @Shared
    private DeterminarPeriodosPerdidosUseCase service
    @Shared
    @Subject
    private PeriodosPerdidosController controller

    void setup() {
        service = Stub(DeterminarPeriodosPerdidosUseCase)
        controller = new PeriodosPerdidosController(service)
    }

    def "Al ocurrir un exception en service, esta es elevada al ControllerAdvice"() {
        given:
        service.calcular() >> { throw new PeriodoDataSourceNoDisponible("ERROR") }
        when:
        controller.getPeriodosPerdidos()
        then:
        def e = thrown(PeriodoDataSourceNoDisponible)
        e.message.contains("ERROR")
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
        PeriodoConFaltantes respuestaController = controller.getPeriodosPerdidos()
        then:
        with(respuestaController) {
            getFechasFaltantes().containsAll(faltantes)
            getId() == respuestaService.getId()
            getFechaCreacion() == respuestaService.getFechaCreacion()
            getFechaFin() == respuestaService.getFechaFin()
            getFechas().containsAll(respuestaService.getFechas())
        }

        where:
        fechaCreacion            | fechaFin                 | fechas                                                                                                                                  | faltantes
        LocalDate.of(2020, 2, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [] as TreeSet
        LocalDate.of(2020, 1, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [LocalDate.parse("2020-02-01")] as TreeSet
        LocalDate.of(1969, 3, 1) | LocalDate.of(1970, 1, 1) | [LocalDate.parse("1969-03-01"), LocalDate.parse("1969-05-01"), LocalDate.parse("1969-09-01"), LocalDate.parse("1970-01-01")] as TreeSet | [LocalDate.parse("1969-04-01"), LocalDate.parse("1969-06-01"), LocalDate.parse("1969-07-01"), LocalDate.parse("1969-08-01"), LocalDate.parse("1969-10-01"), LocalDate.parse("1969-11-01"), LocalDate.parse("1969-12-01")] as TreeSet
    }


}
