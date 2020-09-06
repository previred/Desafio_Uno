package cl.cconcha.desafio1.domain;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Model Periodo")
public class Periodo {

	@ApiModelProperty(value = "id del periodo",example = "1")
	private Long id;
	@ApiModelProperty(value = "fecha de creacion en formato yyyy-mm-dd",example = "2020-04-01")
	private LocalDate fechaCreacion;
	@ApiModelProperty(value = "fecha fin en formato yyyy-mm-dd",example = "2020-09-01")
	private LocalDate fechaFin;
	@ApiModelProperty(value = "lista de fechas",example = "['2020-05-01','2020-07-01','2020-08-01']")
	private List<LocalDate> fechas;
	@ApiModelProperty(value = "lista de fechas faltantes",example = "['2020-04-01','2020-06-01','2020-09-01']")
	private List<LocalDate> fechasFaltantes;
	
	public Periodo() {
		//empty constructor
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
	@Override
	public String toString() {
		return "Periodo [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + ", fechasFaltantes=" + fechasFaltantes + "]";
	}
	
}
