package com.previred.fechas.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapp")
public class Properties {

	private final Map<String, String> myProperties = new HashMap<>();

	public Map<String, String> getProperties() {
		return myProperties;
	}

}