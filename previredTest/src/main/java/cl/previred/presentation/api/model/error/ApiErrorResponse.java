package cl.previred.presentation.api.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wmunoz
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponse implements Serializable {

    private List<ApiError> errors = new ArrayList<>();

    public boolean addError(ApiError apiError) {
        return errors.add(apiError);
    }
}
