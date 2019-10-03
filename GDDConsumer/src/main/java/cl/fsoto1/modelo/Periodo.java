package cl.fsoto1.modelo;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Periodo {

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "1992-03-01")
	private LocalDate fechaCreacion;
	@ApiModelProperty(example = "2019-01-01")
	private LocalDate fechaFin;
	@ApiModelProperty(example = "['1996-03-01','1997-05-01','1998-01-01','1999-05-01']")
	private List<LocalDate> fechas;
	@ApiModelProperty(example = "['2011-03-01','2013-05-01','2014-01-01','2015-05-01']")
	private List<LocalDate> fechasFaltantes;
	
	public Periodo() {
		
	}

	
	public Periodo(Long id) {
		super();
		this.id = id;
	}

	public Periodo(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
	}


	public Periodo(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasFaltantes) {
		super();
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

	@Override
	public String toString() {
		return "Periodo [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + "]";
	}
		
	
}
