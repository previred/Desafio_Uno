package cl.previred.app.utils;

import cl.previred.app.data.dto.ResponseDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Mapper {
	
	private static final Logger logger = LoggerFactory.getLogger(Mapper.class);
	
	public static Gson gson = new Gson();
	
	
	/**
	 *  Método que transforma el string a un objeto DTO 
	 * @param String 
	 * @return ResponseDto
	 * @exception Exception, NullPointerException
	 */
	public static ResponseDto dataToDto(String data) throws Exception, NullPointerException {
		Type listType = new TypeToken<ResponseDto>(){}.getType();
		try {
			return gson.fromJson(data, listType);
		}catch (NullPointerException e) {
			logger.warn("Error en la clase Mapper al pasar S5ring a ResponseDto", e);
			throw e;
		}catch (Exception e) {
			logger.error("Error inesperado en la clase Mapper->dataToDto", e);
			throw e;
		}
		
	}

}
