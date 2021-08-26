package me.jics.exception;

public class DateRangeNotValidException extends RuntimeException {
    public DateRangeNotValidException(String message) {
        super(message);
    }

    public DateRangeNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
