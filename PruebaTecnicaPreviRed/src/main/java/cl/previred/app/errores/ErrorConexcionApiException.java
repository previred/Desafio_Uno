package cl.previred.app.errores;

public class ErrorConexcionApiException extends RuntimeException {

	public ErrorConexcionApiException(String url, String mensaje) {
		super("la url: ".concat(url).concat(" ==> no se encuentra disponible").concat(" << == >> Error:")
				.concat(mensaje));

	}

}
