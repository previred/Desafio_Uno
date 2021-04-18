package com.devru.desafiouno.webservices;

import java.io.Serializable;
import java.util.Map;

public interface WebServiceRequest extends Serializable{
	Map<String, String> getArgumentosWs();
}
