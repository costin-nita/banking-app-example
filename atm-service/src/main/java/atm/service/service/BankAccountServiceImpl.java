package atm.service.service;

import atm.client.dto.bank.account.BankAccountCreateRequest;
import atm.client.dto.bank.account.BankAccountDecrementAmountRequest;
import atm.client.dto.bank.account.BankAccountIncrementAmountRequest;
import atm.client.dto.bank.account.BankAccountResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.service.service.mapper.BankAccountMapper;
import atm.service.domain.Account;
import atm.service.domain.BankAccount;
import atm.service.repository.auth.AccountRepository;
import atm.service.repository.bank.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountRepository accountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountResponse findById(long id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(id));
        }

        BankAccountResponse response = bankAccountMapper.createResponseFromEntity(bankAccount);
        return response;
    }

    @Override
    public List<BankAccountResponse> findByAccountId(long accountId) {
        List<BankAccountResponse> list = bankAccountRepository.findByAccountId(accountId).stream()
                .map(bankAccountMapper::createResponseFromEntity).collect(Collectors.toList());

        return list;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BankAccountResponse create(@Valid BankAccountCreateRequest request) throws AccountNotFoundException {
        Account account = accountRepository.findById(request.getAccountId());
        if (account == null) {
            throw new AccountNotFoundException("id", String.valueOf(request.getAccountId()));
        }

        BankAccount bankAccount = bankAccountMapper.createEntityFromCreateRequest(request, account);
        bankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponse response = bankAccountMapper.createResponseFromEntity(bankAccount);
        return response;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BankAccountResponse incrementAmount(@Valid BankAccountIncrementAmountRequest request) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(request.getId());
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(request.getId()));
        }

        BigDecimal newAmount = bankAccount.getAmount().add(request.getAmount());
        bankAccount.setAmount(newAmount);

        BankAccountResponse response = bankAccountMapper.createResponseFromEntity(bankAccount);
        return response;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BankAccountResponse decrementAmount(@Valid BankAccountDecrementAmountRequest request) throws BankAccountNotFoundException, BankAccountDecrementAboveLimitException {

        BankAccount bankAccount = bankAccountRepository.findById(request.getId());
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(request.getId()));
        }

        BigDecimal newAmount = bankAccount.getAmount().subtract(request.getAmount());
        if (newAmount.compareTo(new BigDecimal(0)) < 0) {
            throw new BankAccountDecrementAboveLimitException(bankAccount.getId(), bankAccount.getAmount(), request.getAmount());
        }

        bankAccount.setAmount(newAmount);

        BankAccountResponse response = bankAccountMapper.createResponseFromEntity(bankAccount);
        return response;
    }
}
