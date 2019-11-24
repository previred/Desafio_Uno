package cl.previred.desafio.component;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("period")
public class Period {
    private int id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaCreacion;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaFin;
    private List<String> fechas;
    private List<String> fechasFaltantes;

    public Period() {
    }

    public Period(int id, Date fechaCreacion, Date fechaFin, List<String> fechas, List<String> fechasFaltantes) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
        this.fechasFaltantes = fechasFaltantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }

    public List<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
}
