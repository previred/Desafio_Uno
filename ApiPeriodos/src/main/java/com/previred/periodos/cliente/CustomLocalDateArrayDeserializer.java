package com.previred.periodos.cliente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

public class CustomLocalDateArrayDeserializer extends JsonDeserializer<List<LocalDate>> {

	  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	  @Override
	  public List<LocalDate> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	    ArrayList<LocalDate> list = new ArrayList<>();
	    JsonToken currentToken = p.getCurrentToken();
	    if (currentToken != JsonToken.START_ARRAY) {
	      throw new JsonMappingException(p, "Not an array!");
	    }

	    currentToken = p.nextToken();

	    while (currentToken != JsonToken.END_ARRAY) {
	      if (currentToken != JsonToken.VALUE_STRING) {
	        throw new JsonMappingException(p, "Not a string element!");
	      }

	      LocalDate localDate = LocalDate.parse(p.getValueAsString(), formatter);
	      list.add(localDate);

	      currentToken = p.nextToken();
	    }

	    return list;
	  }
	}