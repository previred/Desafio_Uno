
package cl.previred.periodosperdidos.backend.rest.bean;

import java.util.List;

/**
 *
 * @author Matias
 */
public class Request {
    
    private long id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }
    
}
