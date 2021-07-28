package atm.service.service.mapper;

import atm.service.domain.BankAccount;
import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.dto.bank.account.BankAccountResponse;
import atm.service.domain.Account;

public interface BankAccountMapper {

    BankAccountResponse createResponseFromEntity(BankAccount entity);

    BankAccount createEntityFromCreateRequest(BankAccountCreateRequest request, Account account);
}
