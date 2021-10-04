package cl.previred.ms.periodos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -6533078367549236908L;

    @NonNull
    private String status;

    @NonNull
    private String message;

}