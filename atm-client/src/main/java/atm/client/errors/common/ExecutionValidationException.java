package atm.client.errors.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class ExecutionValidationException extends Exception {

    private final String errorCode = "global/validation";
    private final List<ValidationError> errors;

    @JsonCreator
    public ExecutionValidationException(@JsonProperty("errors") List<ValidationError> errors) {
        this.errors = errors;
    }
}
