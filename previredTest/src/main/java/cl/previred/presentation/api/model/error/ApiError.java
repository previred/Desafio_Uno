package cl.previred.presentation.api.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wmunoz
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError implements Serializable {

    private String status;
    private String title;
    private String detail;
    private Source source;

    public ApiError(int httpStatus, String title, String detail, String pointer) {
        this.status = String.valueOf(httpStatus);
        this.title = title;
        this.detail = detail;
        this.source = new Source(pointer);
    }

    /**
     * @author wmunoz
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source implements Serializable {
        private String pointer;
    }
}
