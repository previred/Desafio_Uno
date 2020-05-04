package cl.previred.arquitectura.seleccion.lagunas.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * Esta clase combierte una fecha a un String en formato ISO 
 * @author Juan Villablanca
 *
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(LocalDate fecha, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(FORMATTER.format(fecha));
	}

}
