package cl.previred.infrastructure.adapters.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author wmunoz
 */
public class SourceSystemConstraintViolationsException extends ConstraintViolationException {

    @Getter
    private final String system;

    public SourceSystemConstraintViolationsException(String system, Set<ConstraintViolation<Object>> violations) {
        super(violations);
        this.system = system;
    }
}
