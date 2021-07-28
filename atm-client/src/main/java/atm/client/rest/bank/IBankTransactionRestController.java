package atm.client.rest.bank;

import atm.client.dto.bank.common.BankTransactionFilterCriteria;
import atm.client.dto.bank.common.BankTransactionResponse;

import java.util.List;

public interface IBankTransactionRestController {

    List<BankTransactionResponse> getByCriteria(BankTransactionFilterCriteria criteria);
}
