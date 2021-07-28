package atm.client.rest.bank;

import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.deposit.BankDepositTransactionNotFoundException;
import atm.client.errors.auth.account.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface IBankDepositTransactionRestController {

    BankDepositTransactionResponse getById(long accountId, long bankAccountId, long id)
            throws BankDepositTransactionNotFoundException;

    List<BankDepositTransactionResponse> getByCriteria(long accountId, long bankAccountId);

    BankDepositTransactionResponse post(long accountId, long bankAccountId, BigDecimal amount)
            throws BankAccountNotFoundException, AccountNotFoundException;
}
