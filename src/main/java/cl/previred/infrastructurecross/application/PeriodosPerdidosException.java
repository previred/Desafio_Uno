package cl.previred.infrastructurecross.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class PeriodosPerdidosException extends RuntimeException {
	private static final long serialVersionUID = -4291001280313851596L;
	private Integer httpCode;
	private Integer applicationCode;
	public PeriodosPerdidosException() {
	}

	public PeriodosPerdidosException(String errorMessage, Integer httpCode) {
		super(errorMessage);
		this.httpCode = httpCode;
	}

	public PeriodosPerdidosException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public PeriodosPerdidosException(Throwable err) {
		super(err);
	}

	public PeriodosPerdidosException(String message, Integer applicationCode, Integer httpCode) {
		super(message);
		this.httpCode = httpCode;
		this.applicationCode = applicationCode;
	}

	public static ErrorResponseDto generarErrorDto(PeriodosPerdidosException exception) {
		int appCode = exception.getApplicationCode() != null ? exception.getApplicationCode() : 0;
		return new ErrorResponseDto(exception.getMessage(), appCode);
	}
		
	public static ErrorResponseDto generarErrorNoControladoDto(Exception exception) {
		return new ErrorResponseDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
