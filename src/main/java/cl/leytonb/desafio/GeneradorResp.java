package cl.leytonb.desafio;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneradorResp {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "fechaCreacion")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCreacion;

	@JsonProperty(value = "fechaFin")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;

	@JsonProperty(value = "fechas")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private List<LocalDate> fechas;

	public GeneradorResp() {
		// EMPTY FOR JSON
	}

	public GeneradorResp(GeneradorResp other) {
		this.fechaCreacion = other.fechaCreacion;
		this.fechaFin = other.fechaFin;
		this.fechas = other.fechas;
	}

	public final int getId() {
		return id;
	}

	public final LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public final LocalDate getFechaFin() {
		return fechaFin;
	}

	public final List<LocalDate> getFechas() {
		return fechas;
	}

}
