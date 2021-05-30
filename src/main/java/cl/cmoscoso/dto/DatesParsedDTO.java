package cl.cmoscoso.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DatesParsedDTO implements Serializable {
	private static final long serialVersionUID = -2444552119997008391L;

	private Integer id;

	private LocalDate fechaCreacion;

	private LocalDate fechaFin;

	private Set<String> fechas = new HashSet<String>();

	private Set<String> fechasFaltantes = new HashSet<String>();

	public DatesParsedDTO() {
	}

	public DatesParsedDTO(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, Set<String> fechas,
			Set<String> fechasFaltantes) {
		super();
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

	public Set<String> getFechas() {
		return fechas;
	}

	public void setFechas(Set<String> fechas) {
		this.fechas = fechas;
	}

	public Set<String> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(Set<String> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DatesParsedDTO [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + ", fechasFaltantes=" + fechasFaltantes + "]";
	}

}
