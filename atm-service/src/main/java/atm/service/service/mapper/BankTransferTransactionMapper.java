package atm.service.service.mapper;

import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.service.domain.BankAccount;
import atm.service.domain.BankTransferTransaction;
import atm.service.domain.Account;

public interface BankTransferTransactionMapper {

    BankTransferTransactionResponse createResponseFromEntity(BankTransferTransaction entity);

    BankTransferTransaction createEntityFromCreateRequest(BankTransferTransactionCreateRequest request,
                                                          Account ownedBy,
                                                          BankAccount fromBankAccount,
                                                          BankAccount toBankAccount);
}
