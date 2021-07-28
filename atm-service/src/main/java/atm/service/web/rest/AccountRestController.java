package atm.service.web.rest;

import atm.client.dto.auth.account.AccountCreateRequest;
import atm.client.dto.auth.account.AccountResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.rest.auth.account.IAccountRestController;
import atm.service.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@RestController
@RequestMapping("/v1/account")
@AllArgsConstructor
public class AccountRestController implements IAccountRestController {

    private final AccountService accountService;

    @Override
    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable("id") long id) throws AccountNotFoundException {
        AccountResponse response = accountService.findById(id, false);
        return response;
    }

    @Override
    @GetMapping
    public AccountResponse getByEmail(@RequestParam("email") String email) throws AccountNotFoundException {
        AccountResponse response = accountService.findByEmail(email, false);
        return response;
    }

    @Override
    @PostMapping
    public AccountResponse post(@RequestBody AccountCreateRequest request) throws AccountNotFoundException {
        AccountResponse response = accountService.create(request);
        return response;
    }
}
