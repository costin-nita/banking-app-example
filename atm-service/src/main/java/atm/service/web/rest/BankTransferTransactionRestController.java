package atm.service.web.rest;

import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.transfer.BankTransferTransactionNotFoundException;
import atm.service.service.BankTransferTransactionService;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.transfer.BankTransferOnSameAccountException;
import atm.client.rest.bank.IBankTransferTransactionRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/account/{accountId}/bank-transfer")
@AllArgsConstructor
public class BankTransferTransactionRestController implements IBankTransferTransactionRestController {

    private final BankTransferTransactionService bankTransferTransactionService;

    @Override
    @GetMapping("/{id}")
    public BankTransferTransactionResponse getById(@PathVariable("accountId") long accountId,
                                                   @PathVariable("id") long id) throws BankTransferTransactionNotFoundException {

        BankTransferTransactionResponse response = bankTransferTransactionService.findById(id);
        return response;
    }

    @Override
    @GetMapping
    public List<BankTransferTransactionResponse> getByCriteria(@PathVariable("accountId") long accountId,
                                                               @RequestParam("from") Optional<Long> fromBankAccountId,
                                                               @RequestParam("to") Optional<Long> toBankAccountId) {

        List<BankTransferTransactionResponse> list = bankTransferTransactionService
                .findByCriteria(accountId, fromBankAccountId, toBankAccountId);

        return list;
    }

    @Override
    @PostMapping
    public BankTransferTransactionResponse post(@PathVariable("accountId") long accountId,
                                                @RequestBody BankTransferTransactionCreateRequest request)

            throws BankAccountNotFoundException, AccountNotFoundException,
            BankAccountDecrementAboveLimitException, BankTransferOnSameAccountException {

        BankTransferTransactionResponse response = bankTransferTransactionService.create(request);
        return response;
    }
}
