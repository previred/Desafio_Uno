package cl.previred.application.dto.general;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
	private int code;
	private String message;
	private List<DetailError> errors;
}
