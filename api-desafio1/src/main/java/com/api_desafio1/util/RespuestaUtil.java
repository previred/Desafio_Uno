/*
 * Author: Oscar Pinto S.
 * Cargo: Arquitecto en Software
 * Vida Camara S.A.
 * Create Date: 27-03-2020
 */

package com.api_desafio1.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespuestaUtil {

	public static <T> ResponseEntity<T> ok(T objeto) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		responseHeaders.set("Param", "Valor");
		
		return new ResponseEntity<>(objeto, responseHeaders, HttpStatus.OK);
	}

	public static <T> ResponseEntity<T> sinContenido(T objeto) {
		return new ResponseEntity<>(objeto, HttpStatus.NO_CONTENT);
	}
	
	public static <T> ResponseEntity<T> sinImplementacion(T objeto) {
		return new ResponseEntity<>(objeto, HttpStatus.NOT_IMPLEMENTED);
	}
	
	public static <T> ResponseEntity<T> error(T objeto) {
		return new ResponseEntity<>(objeto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
