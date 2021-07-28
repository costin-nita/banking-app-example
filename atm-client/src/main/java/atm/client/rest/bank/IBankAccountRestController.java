package atm.client.rest.bank;

import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.dto.bank.account.BankAccountResponse;

import java.util.List;

public interface IBankAccountRestController {

    BankAccountResponse getById(long accountId, long id) throws BankAccountNotFoundException;

    List<BankAccountResponse> getByCriteria(long accountId);

    BankAccountResponse post(long accountId) throws AccountNotFoundException;
}
