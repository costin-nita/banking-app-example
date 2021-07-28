package atm.service.web.rest;

import atm.client.dto.bank.common.BankTransactionFilterCriteria;
import atm.client.dto.bank.common.BankTransactionResponse;
import atm.service.service.BankTransactionService;
import atm.client.rest.bank.IBankTransactionRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/account/{ownedByAccountId}/bank-transaction")
@AllArgsConstructor
public class BankTransactionRestController implements IBankTransactionRestController {

    private final BankTransactionService bankTransactionService;

    @Override
    @GetMapping
    public List<BankTransactionResponse> getByCriteria(BankTransactionFilterCriteria criteria) {

        List<BankTransactionResponse> list = bankTransactionService.findByCriteria(criteria);
        return list;
    }
}
