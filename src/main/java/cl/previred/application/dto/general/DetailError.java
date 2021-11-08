package cl.previred.application.dto.general;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailError {
	private String domain;
	private String reason;
	private String message;
}
