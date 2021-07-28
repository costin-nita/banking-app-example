package atm.service.web.rest.config;

import atm.client.errors.auth.account.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountAdvice {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountNotFoundException> accountNotFoundHandler(AccountNotFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
