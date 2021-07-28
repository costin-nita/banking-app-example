package atm.service.service.mapper;

import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.service.domain.BankAccount;
import atm.service.domain.BankTransferTransaction;
import atm.service.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class BankTransferTransactionExplicitMapper implements BankTransferTransactionMapper {

    @Override
    public BankTransferTransactionResponse createResponseFromEntity(BankTransferTransaction entity) {
        return new BankTransferTransactionResponse(
                entity.getId(),
                entity.getDateCreated(),
                entity.getDateModified(),
                entity.getOwnedByAccountId(),
                entity.getFromBankAccountId(),
                entity.getToBankAccountId(),
                entity.getAmount()
        );
    }

    @Override
    public BankTransferTransaction createEntityFromCreateRequest(BankTransferTransactionCreateRequest request,
                                                                 Account ownedByAccount,
                                                                 BankAccount fromBankAccount,
                                                                 BankAccount toBankAccount) {

        return new BankTransferTransaction(
                ownedByAccount,
                fromBankAccount,
                toBankAccount,
                request.getAmount()
        );
    }
}
