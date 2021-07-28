package atm.service.service.mapper;

import atm.client.dto.auth.account.AccountResponse;
import atm.client.dto.auth.account.AccountCreateRequest;
import atm.service.domain.Account;

public interface AccountMapper {

    AccountResponse createResponseFromEntity(Account entity, boolean includePassword);

    Account createEntityFromCreateRequest(AccountCreateRequest request);
}
