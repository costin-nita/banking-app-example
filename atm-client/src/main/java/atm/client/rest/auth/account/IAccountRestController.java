package atm.client.rest.auth.account;

import atm.client.dto.auth.account.AccountCreateRequest;
import atm.client.dto.auth.account.AccountResponse;
import atm.client.errors.auth.account.AccountNotFoundException;

public interface IAccountRestController {

    AccountResponse getById(long id) throws AccountNotFoundException;

    AccountResponse getByEmail(String email) throws AccountNotFoundException;

    AccountResponse post(AccountCreateRequest request) throws AccountNotFoundException;
}
