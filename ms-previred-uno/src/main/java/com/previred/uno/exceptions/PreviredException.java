package com.previred.uno.exceptions;

/**
 * Clase para manejar y personalizar Exceptions de Api
 *
 * @author pvillar
 */
public class PreviredException extends Exception{

    private CodeException code;
    private String mensaje;

    public PreviredException (CodeException code, String mensaje, Exception e) {
        super(e.getMessage());
        this.code = code;
        this.mensaje = mensaje;
    }

    public PreviredException(CodeException code, String mensaje) {
        this.code = code;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "PreviredException{" +
                "code=" + code.code +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }

    /**
     * Enum para estandarizar códigos de error de la Api
     *
     */
    public enum CodeException {
        ERROR_NO_CONTROLADO (-1),
        ERROR_RESPONSE (-2),
        ERROR_RESPONSE_SERVER (-3),
        HTTP_STATUS_NOK (-4);

        private final int code;

        CodeException(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
