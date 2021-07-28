package atm.service.service;

import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.transfer.BankTransferOnSameAccountException;
import atm.client.errors.bank.transfer.BankTransferTransactionNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface BankTransferTransactionService {

    BankTransferTransactionResponse findById(long id) throws BankTransferTransactionNotFoundException;

    List<BankTransferTransactionResponse> findByCriteria(long accountId,
                                                         Optional<Long> fromBankAccountId,
                                                         Optional<Long> toBankAccountId);

    BankTransferTransactionResponse create(@Valid BankTransferTransactionCreateRequest request)
            throws BankAccountNotFoundException, AccountNotFoundException,
            BankTransferOnSameAccountException, BankAccountDecrementAboveLimitException;
}
