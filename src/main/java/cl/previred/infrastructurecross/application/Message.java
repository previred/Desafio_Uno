package cl.previred.infrastructurecross.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	private Integer appCode;
	private String message;
	private Integer httpCode;
}
