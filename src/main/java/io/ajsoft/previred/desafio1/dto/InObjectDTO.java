package io.ajsoft.previred.desafio1.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InObjectDTO {

	Integer id;
	String fechaCreacion;
	String fechaFin;
	List<String> fechas;
	
	public InObjectDTO() {
		super();
		this.fechas = new ArrayList<>();
	}
}
