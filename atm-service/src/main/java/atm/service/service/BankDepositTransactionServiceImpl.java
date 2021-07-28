package atm.service.service;

import atm.client.dto.bank.account.BankAccountIncrementAmountRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionCreateRequest;
import atm.client.dto.bank.deposit.BankDepositTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.deposit.BankDepositTransactionNotFoundException;
import atm.service.service.mapper.BankDepositTransactionMapper;
import atm.service.domain.Account;
import atm.service.domain.BankAccount;
import atm.service.domain.BankDepositTransaction;
import atm.service.domain.BankDepositTransaction_;
import atm.service.repository.auth.AccountRepository;
import atm.service.repository.bank.BankAccountRepository;
import atm.service.repository.bank.BankDepositTransactionRepository;
import atm.service.repository.bank.BankDepositTransactionSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class BankDepositTransactionServiceImpl implements BankDepositTransactionService {

    private final BankDepositTransactionRepository bankDepositTransactionRepository;
    private final AccountRepository accountRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankDepositTransactionMapper bankDepositTransactionMapper;
    private final BankAccountService bankAccountService;

    @Override
    public BankDepositTransactionResponse findById(long id) throws BankDepositTransactionNotFoundException {

        BankDepositTransaction bankDepositTransaction = bankDepositTransactionRepository.findById(id);
        if (bankDepositTransaction == null) {
            throw new BankDepositTransactionNotFoundException("id", String.valueOf(id));
        }

        BankDepositTransactionResponse response =
                bankDepositTransactionMapper.createResponseFromEntity(bankDepositTransaction);

        return response;
    }

    @Override
    public List<BankDepositTransactionResponse> findByCriteria(long fromAccountId,
                                                               Optional<Long> toBankAccountId) {

        List<BankDepositTransactionResponse> list = bankDepositTransactionRepository.findAll(
                BankDepositTransactionSpecs.byCriteria(fromAccountId, toBankAccountId),
                new JpaSort(Sort.Direction.DESC, BankDepositTransaction_.dateCreated, BankDepositTransaction_.id)
        )
                .stream()
                .map(bankDepositTransactionMapper::createResponseFromEntity)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BankDepositTransactionResponse create(@Valid BankDepositTransactionCreateRequest request) throws AccountNotFoundException, BankAccountNotFoundException {

        Account fromAccount = accountRepository.findById(request.getFromAccountId());
        if (fromAccount == null) {
            throw new AccountNotFoundException("id", String.valueOf(request.getFromAccountId()));
        }

        BankAccount toBankAccount = bankAccountRepository.findById(request.getToBankAccountId());
        if (toBankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(request.getToBankAccountId()));
        }

        BankDepositTransaction entity = bankDepositTransactionMapper
                .createEntityFromCreateRequest(request, fromAccount, toBankAccount);

        entity = bankDepositTransactionRepository.save(entity);

        bankAccountService.incrementAmount(
                new BankAccountIncrementAmountRequest(request.getToBankAccountId(), request.getAmount())
        );

        BankDepositTransactionResponse response = bankDepositTransactionMapper.createResponseFromEntity(entity);
        return response;
    }
}
