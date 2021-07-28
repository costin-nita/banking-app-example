package atm.service.service.mapper;

import atm.service.domain.BankAccount;
import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.dto.bank.account.BankAccountResponse;
import atm.service.domain.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountExplicitMapper implements BankAccountMapper {

    @Override
    public BankAccountResponse createResponseFromEntity(BankAccount entity) {
        return new BankAccountResponse(
                entity.getId(),
                entity.getDateCreated(),
                entity.getDateModified(),
                entity.getAccountId(),
                entity.getAmount()
        );
    }

    @Override
    public BankAccount createEntityFromCreateRequest(BankAccountCreateRequest request, Account account) {

        return new BankAccount(
                account,
                new BigDecimal(0)
        );
    }
}
