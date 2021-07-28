package atm.service.service;

import atm.client.dto.bank.common.BankTransactionFilterCriteria;
import atm.client.dto.bank.common.BankTransactionResponse;

import java.util.List;

public interface BankTransactionService {

    List<BankTransactionResponse> findByCriteria(BankTransactionFilterCriteria criteria);
}
