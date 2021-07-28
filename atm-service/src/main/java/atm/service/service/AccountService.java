package atm.service.service;

import atm.client.dto.auth.account.AccountCreateRequest;
import atm.client.dto.auth.account.AccountResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface AccountService {

    AccountResponse findById(long id, boolean includePassword) throws AccountNotFoundException;

    AccountResponse findByEmail(String email, boolean includePassword) throws AccountNotFoundException;

    AccountResponse create(@Valid AccountCreateRequest request) throws AccountNotFoundException;
}
