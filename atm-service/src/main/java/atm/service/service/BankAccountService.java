package atm.service.service;

import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.dto.bank.account.BankAccountDecrementAmountRequest;
import atm.client.dto.bank.account.BankAccountResponse;
import atm.client.dto.bank.account.BankAccountIncrementAmountRequest;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface BankAccountService {

    BankAccountResponse findById(long id) throws BankAccountNotFoundException;

    List<BankAccountResponse> findByAccountId(long accountId);

    BankAccountResponse create(@Valid BankAccountCreateRequest request) throws AccountNotFoundException;

    BankAccountResponse incrementAmount(@Valid BankAccountIncrementAmountRequest request) throws BankAccountNotFoundException;

    BankAccountResponse decrementAmount(@Valid BankAccountDecrementAmountRequest request) throws BankAccountNotFoundException, BankAccountDecrementAboveLimitException;
}
