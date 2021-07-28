package atm.service.service;

import atm.client.dto.bank.account.BankAccountDecrementAmountRequest;
import atm.client.dto.bank.account.BankAccountIncrementAmountRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionCreateRequest;
import atm.client.dto.bank.transfer.BankTransferTransactionResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import atm.client.errors.bank.account.BankAccountDecrementAboveLimitException;
import atm.client.errors.bank.account.BankAccountNotFoundException;
import atm.client.errors.bank.transfer.BankTransferOnSameAccountException;
import atm.client.errors.bank.transfer.BankTransferTransactionNotFoundException;
import atm.service.service.mapper.BankTransferTransactionMapper;
import atm.service.domain.Account;
import atm.service.domain.BankAccount;
import atm.service.domain.BankTransferTransaction;
import atm.service.domain.BankTransferTransaction_;
import atm.service.repository.auth.AccountRepository;
import atm.service.repository.bank.BankAccountRepository;
import atm.service.repository.bank.BankTransferTransactionRepository;
import atm.service.repository.bank.BankTransferTransactionSpecs;
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
public class BankTransferTransactionServiceImpl implements BankTransferTransactionService {

    private final BankTransferTransactionRepository bankTransferTransactionRepository;
    private final AccountRepository accountRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankTransferTransactionMapper bankTransferTransactionMapper;
    private final BankAccountService bankAccountService;

    @Override
    public BankTransferTransactionResponse findById(long id) throws BankTransferTransactionNotFoundException {

        BankTransferTransaction bankTransferTransaction = bankTransferTransactionRepository.findById(id);
        if (bankTransferTransaction == null) {
            throw new BankTransferTransactionNotFoundException("id", String.valueOf(id));
        }

        BankTransferTransactionResponse response =
                bankTransferTransactionMapper.createResponseFromEntity(bankTransferTransaction);

        return response;
    }

    @Override
    public List<BankTransferTransactionResponse> findByCriteria(long accountId,
                                                                Optional<Long> fromBankAccountId,
                                                                Optional<Long> toBankAccountId) {

        List<BankTransferTransactionResponse> list = bankTransferTransactionRepository.findAll(
                BankTransferTransactionSpecs.byCriteria(accountId, fromBankAccountId, toBankAccountId),
                new JpaSort(Sort.Direction.DESC, BankTransferTransaction_.dateCreated, BankTransferTransaction_.id)
        )
                .stream()
                .map(bankTransferTransactionMapper::createResponseFromEntity)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BankTransferTransactionResponse create(@Valid BankTransferTransactionCreateRequest request)
            throws BankAccountNotFoundException, AccountNotFoundException,
            BankTransferOnSameAccountException, BankAccountDecrementAboveLimitException {

        if (request.getFromBankAccountId() == request.getToBankAccountId()) {
            throw new BankTransferOnSameAccountException(request.getFromBankAccountId());
        }

        BankAccount fromBankAccount = bankAccountRepository.findById(request.getFromBankAccountId());
        if (fromBankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(request.getFromBankAccountId()));
        }

        BankAccount toBankAccount = bankAccountRepository.findById(request.getToBankAccountId());
        if (toBankAccount == null) {
            throw new BankAccountNotFoundException("id", String.valueOf(request.getToBankAccountId()));
        }

        Account ownedByAccount = accountRepository.findById(fromBankAccount.getAccountId());
        if (ownedByAccount == null) {
            throw new AccountNotFoundException("id", String.valueOf(fromBankAccount.getAccountId()));
        }

        bankAccountService.decrementAmount(
                new BankAccountDecrementAmountRequest(request.getFromBankAccountId(), request.getAmount())
        );

        bankAccountService.incrementAmount(
                new BankAccountIncrementAmountRequest(request.getToBankAccountId(), request.getAmount())
        );

        BankTransferTransaction bankTransferTransaction = bankTransferTransactionMapper.createEntityFromCreateRequest(
                request, ownedByAccount, fromBankAccount, toBankAccount);

        bankTransferTransaction = bankTransferTransactionRepository.save(bankTransferTransaction);

        BankTransferTransactionResponse response =
                bankTransferTransactionMapper.createResponseFromEntity(bankTransferTransaction);

        return response;
    }
}
