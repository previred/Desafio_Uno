package cl.previred.ms.periodos.services.utils;

import org.springframework.http.HttpHeaders;

/**
* Utility class for generic response headers.
*/
public class HeadersUtils {

	private HeadersUtils() {
		/* Private Constructor will prevent the instantiation of this class directly */
	};

	public final static HttpHeaders getGenericHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "*");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Credentials", "false");
		headers.add("Access-Control-Allow-Headers", "*");
		headers.add("X-Frame-Options", "deny");
		headers.add("X-XSS-Protection", "1; mode=block");
		headers.add("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		return headers;
	}

}