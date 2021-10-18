package com.previred.desafiouno.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.previred.desafiouno.type.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseDTO<T> {
	private ResultCode resultado;
	private String mensaje;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T datos;
}
