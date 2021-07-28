package atm.service.service;

import atm.client.dto.auth.account.AccountCreateRequest;
import atm.client.dto.auth.account.AccountResponse;
import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.service.service.mapper.AccountMapper;
import atm.service.domain.Account;
import atm.service.repository.auth.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final BankAccountService bankAccountService;

    @Override
    public AccountResponse findById(long id, boolean includePassword) throws AccountNotFoundException {
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new AccountNotFoundException("id", String.valueOf(id));
        }

        AccountResponse response = accountMapper.createResponseFromEntity(account, includePassword);
        return response;
    }

    @Override
    public AccountResponse findByEmail(String email, boolean includePassword) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new AccountNotFoundException("email", email);
        }

        AccountResponse response = accountMapper.createResponseFromEntity(account, includePassword);
        return response;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountResponse create(@Valid AccountCreateRequest request) throws AccountNotFoundException {

        Account account = accountMapper.createEntityFromCreateRequest(request);
        account = accountRepository.save(account);

        BankAccountCreateRequest bankAccountCreateRequest = new BankAccountCreateRequest(account.getId());
        bankAccountService.create(bankAccountCreateRequest);

        AccountResponse response = accountMapper.createResponseFromEntity(account, false);
        return response;
    }
}
