package atm.client.rest.bank;

import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.transfer.BankTransferOnSameAccountException;
import atm.client.errors.bank.transfer.BankTransferTransactionNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IBankTransferTransactionRestController {

    BankTransferTransactionResponse getById(long accountId, long id)
            throws BankTransferTransactionNotFoundException;

    List<BankTransferTransactionResponse> getByCriteria(long accountId,
                                                        Optional<Long> fromBankAccountId,
                                                        Optional<Long> toBankAccountId);

    BankTransferTransactionResponse post(long accountId, BankTransferTransactionCreateRequest request)
            throws BankAccountNotFoundException, AccountNotFoundException,
            BankAccountDecrementAboveLimitException, BankTransferOnSameAccountException;
}
