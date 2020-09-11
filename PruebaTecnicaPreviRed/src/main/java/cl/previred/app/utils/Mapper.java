package cl.previred.app.utils;

import cl.previred.app.data.dto.ResponseDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class Mapper {
	
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
			throw e;
		}catch (Exception e) {
			throw e;
		}
		
	}

}
