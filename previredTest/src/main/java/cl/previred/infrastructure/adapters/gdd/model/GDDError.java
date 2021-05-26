package cl.previred.infrastructure.adapters.gdd.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wmunoz
 */
@Getter
@Setter
@NoArgsConstructor
public class GDDError implements Serializable {

    private String date;
    private String message;
    private boolean ok;

}
