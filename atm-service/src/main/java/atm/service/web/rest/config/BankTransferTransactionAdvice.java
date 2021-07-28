package atm.service.web.rest.config;

import atm.client.errors.bank.transfer.BankTransferTransactionNotFoundException;
import atm.client.errors.bank.transfer.BankTransferOnSameAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankTransferTransactionAdvice {

    @ExceptionHandler(BankTransferTransactionNotFoundException.class)
    public ResponseEntity<BankTransferTransactionNotFoundException> bankTransferTransactionNotFoundHandler(
            BankTransferTransactionNotFoundException ex) {

        return new ResponseEntity<BankTransferTransactionNotFoundException>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankTransferOnSameAccountException.class)
    public ResponseEntity<BankTransferOnSameAccountException> bankTransferOnSameAccountHandler(
            BankTransferOnSameAccountException ex) {

        return new ResponseEntity<BankTransferOnSameAccountException>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
