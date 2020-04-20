package com.previred.periodos.determinafaltantes.core

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class DeterminarPeriodosPerdidosServiceSpec extends Specification {
    @Shared
    private PeriodoPort port
    @Subject
    @Shared
    private DeterminarPeriodosPerdidosUseCase service

    void setup() {
        port = Stub(PeriodoPort)
        service = new DeterminarPeriodosPerdidosService(port)

    }

    def "al obtener un Periodo nulo, se retorna un error de servicio no disponible"() {
        given:
        port.getFechasPeriodoAleatorio() >> Periodo.PERIODO_NULO
        when:
        service.calcular()
        then:
        def e = thrown(PeriodoDataSourceNoDisponible)
        e.message.contains("La consulta de las fechas del period aleatorio no fue satisfactoria")
    }

    def "calcula las fechas faltantes, la lista de fechas del periodo"() {
        given:
        def respuestaPort = Periodo.builder().id(1)
                .fechaCreacion(fechaCreacion)
                .fechaFin(fechaFin)
                .fechas(fechas)
                .build()
        port.getFechasPeriodoAleatorio() >> respuestaPort

        when:
        PeriodoConFaltantes periodo = service.calcular()

        then:
        with(periodo) {
            getFechasFaltantes().containsAll(faltantes)
            getFechasFaltantes().size() == faltantes.size()
            getId() == respuestaPort.getId()
            getFechaCreacion() == respuestaPort.getFechaCreacion()
            getFechaFin() == respuestaPort.getFechaFin()
            getFechas().containsAll(respuestaPort.getFechas())
            getFechas().size() == respuestaPort.getFechas().size()
        }

        where:
        fechaCreacion            | fechaFin                 | fechas                                                                                                                                  | faltantes
        LocalDate.of(2020, 2, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [] as TreeSet
        LocalDate.of(2020, 1, 1) | LocalDate.of(2020, 4, 1) | [LocalDate.parse("2020-03-01")] as TreeSet                                                                                              | [LocalDate.parse("2020-02-01")] as TreeSet
        LocalDate.of(1969, 3, 1) | LocalDate.of(1970, 1, 1) | [LocalDate.parse("1969-03-01"), LocalDate.parse("1969-05-01"), LocalDate.parse("1969-09-01"), LocalDate.parse("1970-01-01")] as TreeSet | [LocalDate.parse("1969-04-01"),LocalDate.parse("1969-06-01"),LocalDate.parse("1969-07-01"),LocalDate.parse("1969-08-01"),LocalDate.parse("1969-10-01"),LocalDate.parse("1969-11-01"),LocalDate.parse("1969-12-01")] as TreeSet
    }


}
