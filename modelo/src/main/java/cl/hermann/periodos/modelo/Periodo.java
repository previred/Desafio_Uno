package cl.hermann.periodos.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author hylianred
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","fechaCreacion","fechaFin","fechas", "fechasFaltantes"})
public class Periodo {

    private Long id = null;

    private LocalDate fechaCreacion = null;

    private LocalDate fechaFin = null;

    private List<LocalDate> fechas = null;
    
    private List<LocalDate> fechasFaltantes = null;

    public Periodo(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas, List<LocalDate> fechasFaltantes) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
        this.fechasFaltantes = fechasFaltantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }

    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

}
