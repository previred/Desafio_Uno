package io.ajsoft.previred.desafio1.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutObjectDTO {
	
	Integer id;
	String fechaCreacion;
	String fechaFin;
	List<String> fechas;
	List<String> fechasFaltantes;
	
	public OutObjectDTO() {
		super();
		this.fechas = new ArrayList<>();
		this.fechasFaltantes = new ArrayList<>();
	}
}
