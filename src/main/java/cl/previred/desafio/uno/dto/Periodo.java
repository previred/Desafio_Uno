package cl.previred.desafio.uno.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Periodo implements Serializable {
	private static final long serialVersionUID = 8794958232738564063L;

	private Long id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;

	public Periodo() {
	}

	public Periodo(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Periodo[");
		sb.append("id=" + id);
		sb.append(", fechaCreacion=" + fechaCreacion);
		sb.append(", fechaFin=" + fechaFin);
		sb.append(", fechas=" + fechas);
		sb.append("]");
		return sb.toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private LocalDate fechaCreacion;
		private LocalDate fechaFin;
		private List<LocalDate> fechas;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder fechaCreacion(LocalDate fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
			return this;
		}

		public Builder fechaFin(LocalDate fechaFin) {
			this.fechaFin = fechaFin;
			return this;
		}

		public Builder fechas(List<LocalDate> fechas) {
			this.fechas = fechas;
			return this;
		}

		public Periodo build() {
			return new Periodo(id, fechaCreacion, fechaFin, fechas);
		}
	}

}
