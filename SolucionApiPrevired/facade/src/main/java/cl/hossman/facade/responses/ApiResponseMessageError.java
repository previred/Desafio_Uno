package cl.hossman.facade.responses;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlTransient;

@javax.xml.bind.annotation.XmlRootElement
public class ApiResponseMessageError {

    String code;
    String type;
    String message;
    LocalDateTime timestamp;
    
    public ApiResponseMessageError(){}

    public ApiResponseMessageError(String code, String message, String type){
        this.code = code;
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    @XmlTransient
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
