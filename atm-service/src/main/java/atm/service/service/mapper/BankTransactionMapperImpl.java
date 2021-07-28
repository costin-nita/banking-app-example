package atm.service.service.mapper;

import atm.client.dto.bank.common.BankTransactionResponse;
import atm.service.domain.BankTransaction;
import org.springframework.stereotype.Service;

@Service
public class BankTransactionMapperImpl implements BankTransactionMapper {

    @Override
    public BankTransactionResponse createResponseFromEntity(BankTransaction entity) {
        return new BankTransactionResponse(
                entity.getId().getTransactionType(),
                entity.getId().getTransactionId(),
                entity.getOwnedByAccountId(),
                entity.getFromAccountId(),
                entity.getFromBankAccountId(),
                entity.getToBankAccountId(),
                entity.getAmount(),
                entity.getDateCreated(),
                entity.getDateModified()
        );
    }
}
