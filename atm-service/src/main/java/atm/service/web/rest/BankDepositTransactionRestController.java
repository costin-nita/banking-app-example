package atm.service.web.rest;

import atm.client.dto.bank.deposit.BankDepositTransactionCreateRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.deposit.BankDepositTransactionNotFoundException;
import atm.client.rest.bank.IBankDepositTransactionRestController;
import atm.service.service.BankDepositTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/account/{accountId}/bank-account/{bankAccountId}/bank-deposit")
@AllArgsConstructor
public class BankDepositTransactionRestController implements IBankDepositTransactionRestController {

    private final BankDepositTransactionService bankDepositTransactionService;

    @Override
    @GetMapping("/{id}")
    public BankDepositTransactionResponse getById(@PathVariable("accountId") long accountId,
                                                  @PathVariable("bankAccountId") long bankAccountId,
                                                  @PathVariable("id") long id) throws BankDepositTransactionNotFoundException {

        BankDepositTransactionResponse response = bankDepositTransactionService.findById(id);
        return response;
    }

    @Override
    @GetMapping
    public List<BankDepositTransactionResponse> getByCriteria(@PathVariable("accountId") long accountId,
                                                              @PathVariable("bankAccountId") long bankAccountId) {

        List<BankDepositTransactionResponse> list = bankDepositTransactionService
                .findByCriteria(accountId, Optional.of(bankAccountId));

        return list;
    }

    @Override
    @PostMapping
    public BankDepositTransactionResponse post(@PathVariable("accountId") long accountId,
                                               @PathVariable("bankAccountId") long bankAccountId,
                                               @RequestBody BigDecimal amount) throws BankAccountNotFoundException, AccountNotFoundException {

        BankDepositTransactionCreateRequest request =
                new BankDepositTransactionCreateRequest(accountId, bankAccountId, amount);

        BankDepositTransactionResponse response = bankDepositTransactionService.create(request);
        return response;
    }
}
