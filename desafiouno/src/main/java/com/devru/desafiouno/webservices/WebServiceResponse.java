package com.devru.desafiouno.webservices;

import java.io.Serializable;

public interface WebServiceResponse extends Serializable {
	String getRazon();
	int getCodigoRespuesta();
}
