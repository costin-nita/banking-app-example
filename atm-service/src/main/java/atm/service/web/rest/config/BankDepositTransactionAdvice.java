package atm.service.web.rest.config;

import atm.client.errors.bank.deposit.BankDepositTransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankDepositTransactionAdvice {

    @ExceptionHandler(BankDepositTransactionNotFoundException.class)
    public ResponseEntity<BankDepositTransactionNotFoundException> bankDepositTransactionNotFoundHandler(
            BankDepositTransactionNotFoundException ex) {

        return new ResponseEntity<BankDepositTransactionNotFoundException>(ex, HttpStatus.NOT_FOUND);
    }
}
