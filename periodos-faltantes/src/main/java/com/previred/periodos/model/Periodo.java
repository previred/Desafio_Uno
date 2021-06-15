package com.previred.periodos.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

@Validated
public class Periodo {
	  @JsonProperty("id")
	  private Long id;
	
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	  @JsonProperty("fechaCreacion")
	  private LocalDate fechaCreacion;
	
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	  @JsonProperty("fechaFin")
	  private LocalDate fechaFin;
	
	  @JsonProperty("fechas")
	  @JsonInclude(JsonInclude.Include.NON_NULL) 
	  private @Nullable List<LocalDate> fechas;
	  
	  @JsonProperty("fechasFaltantes")
	  @JsonInclude(JsonInclude.Include.NON_NULL) 
	  private @Nullable List<LocalDate> fechasFaltantes;

	  
	  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LocalDate> getFechas() {
		return fechas;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechas == null) ? 0 : fechas.hashCode());
		result = prime * result + ((fechasFaltantes == null) ? 0 : fechasFaltantes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechas == null) {
			if (other.fechas != null)
				return false;
		} else if (!fechas.equals(other.fechas))
			return false;
		if (fechasFaltantes == null) {
			if (other.fechasFaltantes != null)
				return false;
		} else if (!fechasFaltantes.equals(other.fechasFaltantes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Periodo [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + ", fechasFaltantes=" + fechasFaltantes + "]";
	}

  
  
  
}