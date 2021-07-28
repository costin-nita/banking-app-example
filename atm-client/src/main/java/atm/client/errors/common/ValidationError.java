package atm.client.errors.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.ConstraintViolation;

@Getter
public class ValidationError {

    private final String beanName;
    private final String propertyPath;
    private final String message;

    @JsonCreator
    public ValidationError(@JsonProperty("beanName") String beanName,
                           @JsonProperty("propertyPath") String propertyPath,
                           @JsonProperty("message") String message) {

        this.beanName = beanName;
        this.propertyPath = propertyPath;
        this.message = message;
    }

    public ValidationError(ConstraintViolation violation) {
        this.beanName = violation.getRootBeanClass().getName();
        this.propertyPath = violation.getPropertyPath().toString();
        this.message = violation.getMessage();
    }
}
