package atm.service.service.mapper;

import atm.client.dto.bank.deposit.BankDepositTransactionCreateRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.service.domain.BankAccount;
import atm.service.domain.BankDepositTransaction;
import atm.service.domain.Account;

public interface BankDepositTransactionMapper {

    BankDepositTransactionResponse createResponseFromEntity(BankDepositTransaction entity);

    BankDepositTransaction createEntityFromCreateRequest(BankDepositTransactionCreateRequest request,
                                                         Account fromAccount,
                                                         BankAccount toBankAccount);
}
