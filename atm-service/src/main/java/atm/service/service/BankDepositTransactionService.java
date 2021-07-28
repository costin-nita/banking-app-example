package atm.service.service;

import atm.client.dto.bank.deposit.BankDepositTransactionCreateRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.deposit.BankDepositTransactionNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface BankDepositTransactionService {

    BankDepositTransactionResponse findById(long id) throws BankDepositTransactionNotFoundException;

    List<BankDepositTransactionResponse> findByCriteria(long fromAccountId,
                                                        Optional<Long> toBankAccountId);

    BankDepositTransactionResponse create(@Valid BankDepositTransactionCreateRequest request) throws AccountNotFoundException, BankAccountNotFoundException;
}
