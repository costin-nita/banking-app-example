package atm.service.service.mapper;

import atm.client.dto.bank.common.BankTransactionResponse;
import atm.service.domain.BankTransaction;

public interface BankTransactionMapper {

    BankTransactionResponse createResponseFromEntity(BankTransaction entity);
}
