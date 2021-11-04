package com.cbravo;

public class Respuesta extends Entrada  {
	private String[] fechasFaltantes;
	
	
	Respuesta(Entrada ent, String[] fechFal){
		super(ent.getId(),ent.getFechaCreacion(), ent.getFechaFin(), ent.getFechas());
		fechasFaltantes = fechFal;
	}
	

	public String[] getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(String[] fechas) {
		this.fechasFaltantes = fechas;
	}
}