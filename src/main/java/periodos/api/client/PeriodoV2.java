package periodos.api.client;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Modelo de Periodo que contiene solo las fechas faltantes
 * @author Jorge Silva Borda
 */
public class PeriodoV2 {
    Integer id;
    LocalDate fechaCreacion;
    LocalDate fechaFin;
    Collection<LocalDate> fechas;
    Collection<LocalDate> fechasFaltantes;

    public PeriodoV2() {
    }

    public PeriodoV2(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, Collection<LocalDate> fechas, Collection<LocalDate> fechasFaltantes) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
        this.fechasFaltantes = fechasFaltantes;
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

    public Collection<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(Collection<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

    
}
