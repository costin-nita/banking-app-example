package atm.service.web.rest;


import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.dto.bank.account.BankAccountResponse;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.service.service.BankAccountService;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.rest.bank.IBankAccountRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account/{accountId}/bank-account")
@AllArgsConstructor
public class BankAccountRestController implements IBankAccountRestController {

    private final BankAccountService bankAccountService;

    @Override
    @GetMapping("{id}")
    public BankAccountResponse getById(@PathVariable("accountId") long accountId,
                                       @PathVariable("id") long id) throws BankAccountNotFoundException {

        BankAccountResponse response = bankAccountService.findById(id);
        return response;
    }

    @Override
    @GetMapping
    public List<BankAccountResponse> getByCriteria(@PathVariable("accountId") long accountId) {

        List<BankAccountResponse> list = bankAccountService.findByAccountId(accountId);
        return list;
    }

    @Override
    @PostMapping
    public BankAccountResponse post(@PathVariable("accountId") long accountId) throws AccountNotFoundException {

        BankAccountCreateRequest request = new BankAccountCreateRequest(accountId);
        BankAccountResponse response = bankAccountService.create(request);
        return response;
    }
}
