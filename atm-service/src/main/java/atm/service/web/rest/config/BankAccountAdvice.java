package atm.service.web.rest.config;

import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankAccountAdvice {

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<BankAccountNotFoundException> bankAccountNotFoundHandler(BankAccountNotFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankAccountDecrementAboveLimitException.class)
    public ResponseEntity<BankAccountDecrementAboveLimitException> bankAccountDecrementAboveLimitHandler(
            BankAccountDecrementAboveLimitException ex) {

        return new ResponseEntity<BankAccountDecrementAboveLimitException>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
