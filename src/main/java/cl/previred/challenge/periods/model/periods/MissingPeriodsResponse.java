package cl.previred.challenge.periods.model.periods;

import java.time.LocalDate;
import java.util.List;

/**
 * Model Response from Missing Perdiods
 *
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
public class MissingPeriodsResponse {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    private List<LocalDate> fechas;
    private List<LocalDate> fechasFaltantes;

    public MissingPeriodsResponse(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
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
