package cl.previred.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import cl.previred.utils.DateFormatEnum;
import cl.previred.utils.AppUtils;

public class DateListHandler extends StdDeserializer<List<Date>> {


	private static final long serialVersionUID = -3811185982167631583L;

	public DateListHandler() {
		super(Date.class);
	}

	public DateListHandler(Class<?> vc) {
		super(vc);
	}

	@Override
	public List<Date> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
	
		List<Date> dates = new ArrayList<>();

		node.forEach(jsonNode -> {

			try {
				Date date = AppUtils.dateFormatFactory(DateFormatEnum.FORMATO_ESTANDAR).parse(jsonNode.toString().replace("\"",""));
				dates.add(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		});

		return dates;
	}

}