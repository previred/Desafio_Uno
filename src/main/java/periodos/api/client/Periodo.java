package periodos.api.client;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Clase para el modelo de Periodo
 * @author Jorge Silva Borda
 */
public class Periodo {
    Integer id;
    LocalDate fechaCreacion;
    LocalDate fechaFin;
    Collection<LocalDate> fechas;

    public Periodo() {
    }

    public Periodo(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, Collection<LocalDate> fechas) {
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

    public Collection<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(Collection<LocalDate> fechas) {
        this.fechas = fechas;
    }
    
    
}
