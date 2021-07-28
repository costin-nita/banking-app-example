package atm.service.web.rest.config;

import atm.client.errors.common.ExecutionValidationException;
import atm.client.errors.common.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExecutionValidationException> constraintViolationHandler(ConstraintViolationException ex) {

        List<ValidationError> validationErrors = ex.getConstraintViolations().stream()
                .map(ValidationError::new).collect(Collectors.toList());

        ExecutionValidationException executionValidationException = new ExecutionValidationException(validationErrors);

        return new ResponseEntity<ExecutionValidationException>(executionValidationException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ExecutionValidationException> executionValidationHandler(ExecutionValidationException ex) {
        return new ResponseEntity<ExecutionValidationException>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
