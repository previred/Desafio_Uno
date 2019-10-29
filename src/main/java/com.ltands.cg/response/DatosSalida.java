package com.ltands.cg.response;

import com.ltands.cg.request.DatosEntrada;

import java.util.List;

public class DatosSalida {

	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
	private List<String> fechasFaltantes;


	public DatosSalida (){};

    public DatosSalida (DatosEntrada datosEntrada, List<String> fechasFaltantes){
        this.id = datosEntrada.getId();
        this.fechaCreacion = datosEntrada.getFechaCreacion();
        this.setFechas(datosEntrada.getFechas());
        this.setFechasFaltantes(fechasFaltantes);

    };



	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<String> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<String> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
}
