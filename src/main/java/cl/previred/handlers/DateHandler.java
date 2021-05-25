package cl.previred.handlers;


import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import cl.previred.utils.DateFormatEnum;
import cl.previred.utils.AppUtils;

public class DateHandler extends StdDeserializer<Date> {

	private static final long serialVersionUID = 6560732324291240416L;

	public DateHandler() {
		this(null);
	}

	public DateHandler(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String date = jsonparser.getText();
		try {
			
			return AppUtils.dateFormatFactory(DateFormatEnum.FORMATO_ESTANDAR).parse(date);

		} catch (Exception e) {
			return null;
		}
	}

}
