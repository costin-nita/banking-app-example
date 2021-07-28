package atm.service.service;

import atm.client.dto.bank.common.BankTransactionFilterCriteria;
import atm.client.dto.bank.common.BankTransactionResponse;
import atm.service.service.mapper.BankTransactionMapper;
import atm.service.domain.BankTransaction_;
import atm.service.repository.bank.BankTransactionRepository;
import atm.service.repository.bank.BankTransactionSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankTransactionServiceImpl implements BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;
    private final BankTransactionMapper bankTransactionMapper;

    @Override
    public List<BankTransactionResponse> findByCriteria(BankTransactionFilterCriteria criteria) {

        List<BankTransactionResponse> list = bankTransactionRepository.findAll(
                BankTransactionSpecs.byCriteria(criteria),
                new JpaSort(Sort.Direction.DESC, BankTransaction_.dateCreated, BankTransaction_.id)
        )
                .stream()
                .map(bankTransactionMapper::createResponseFromEntity)
                .collect(Collectors.toList());

        return list;
    }
}
