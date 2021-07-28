package atm.service.service.mapper;

import atm.client.dto.bank.deposit.BankDepositTransactionCreateRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.service.domain.BankAccount;
import atm.service.domain.BankDepositTransaction;
import atm.service.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class BankDepositTransactionExplicitMapper implements BankDepositTransactionMapper {

    @Override
    public BankDepositTransactionResponse createResponseFromEntity(BankDepositTransaction entity) {
        return new BankDepositTransactionResponse(
                entity.getId(),
                entity.getDateCreated(),
                entity.getDateModified(),
                entity.getFromAccountId(),
                entity.getToBankAccountId(),
                entity.getAmount()
        );
    }

    @Override
    public BankDepositTransaction createEntityFromCreateRequest(BankDepositTransactionCreateRequest request,
                                                                Account fromAccount,
                                                                BankAccount toBankAccount) {

        return new BankDepositTransaction(
                fromAccount,
                toBankAccount,
                request.getAmount()
        );
    }
}
