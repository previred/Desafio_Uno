package cl.previred.infrastructurecross.application;

import java.io.PrintWriter;
import java.io.StringWriter;
import cl.previred.application.dto.general.Error;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
	private Error error;

	public ErrorResponseDto(String message, int code) {
		this.error = new Error();
		this.error.setCode(code);
		this.error.setMessage(message);
	}
		
	public ErrorResponseDto(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));	
		String errorComplete = sw.toString();
		
		this.error = new Error();
		this.error.setCode(500);
		this.error.setMessage(errorComplete);
	}
}
