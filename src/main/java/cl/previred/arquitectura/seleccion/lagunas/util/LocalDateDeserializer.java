package cl.previred.arquitectura.seleccion.lagunas.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


/**
 * Esta clase combierte un String que tiene una fecha en formato ISO a una fecha LocalDate
 * @author Juan Villablanca
 *
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate>{
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext arg1) throws IOException {
		 return LocalDate.parse(p.getValueAsString(), FORMATTER);
	}

	/**
	 * Se combierte la fecha a formato ISO
	 * @param fecIso
	 * @return
	 */
	public static LocalDate getLocalDate(String fecIso) 
	{
		return LocalDate.parse(fecIso, FORMATTER);
	}
}
