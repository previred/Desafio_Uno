package cl.previred.desafio.service;

import cl.previred.desafio.utils.ProcesamientoFecha;
import cl.previred.desafio.vo.Solucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class N3Service {

    @Autowired
    private ApiPeriodosProxy proxy;

    @Autowired
    private ProcesamientoFecha procesamientoFecha;

    /**
     * Retorna un objeto {@link Solucion}, con una estructura similar a la entidad {@link Periodo},
     * solo que este incorpora además una lista de FechasRestantes
     * @return
     */
    public Solucion obtenerSolucion(){
        Solucion solucion = new Solucion();
        ResponseEntity<Periodo> periodos = proxy.getPeriodos();
        System.out.println(periodos.toString());
        List<LocalDate> fechasFaltantes = procesamientoFecha.obtenerFechasFaltantes(periodos.getBody());
        solucion.setId(periodos.getBody().getId());
        solucion.setFechaCreacion(periodos.getBody().getFechaCreacion());
        solucion.setFechaFin(periodos.getBody().getFechaFin());
        solucion.setFechas(periodos.getBody().getFechas());
        solucion.setFechasFaltantes(fechasFaltantes);
        System.out.println(solucion.toString());
        return solucion;
    }
}
