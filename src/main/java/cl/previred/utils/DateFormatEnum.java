package cl.previred.utils;


public enum DateFormatEnum {

	FORMATO_SUFIJO_ARCHIVO("yyyyMMddHHmm"), FORMATO_HORA_MIN_SEG("HH:mm:ss"), FORMATO_YYYYMMDD(
			"yyyyMMdd"), FORMATO_CORREO("dd-MM-yyy HH:mm"),FORMATO_ESTANDAR("yyyy-MM-dd");

	private String pattern;

	DateFormatEnum(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}

}
