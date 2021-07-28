package atm.service.repository.bank;

import atm.client.dto.bank.common.BankTransactionFilterCriteria;
import atm.service.domain.BankTransaction;
import atm.service.domain.BankTransaction_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class BankTransactionSpecs {

    public static Specification<BankTransaction> byCriteria(BankTransactionFilterCriteria criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                // ownedByAccountId
                Optional.ofNullable(criteria.getOwnedByAccountId())
                        .map(id -> criteriaBuilder.equal(root.get(BankTransaction_.ownedByAccountId), id))
                        .orElse(criteriaBuilder.and()),
                // fromAccountId
                Optional.ofNullable(criteria.getFromAccountId())
                        .map(id -> criteriaBuilder.equal(root.get(BankTransaction_.fromAccountId), id))
                        .orElse(criteriaBuilder.and()),
                // fromBankAccountId
                Optional.ofNullable(criteria.getFromBankAccountId())
                        .map(id -> criteriaBuilder.equal(root.get(BankTransaction_.fromBankAccountId), id))
                        .orElse(criteriaBuilder.and()),
                // toBankAccountId
                Optional.ofNullable(criteria.getToBankAccountId())
                        .map(id -> criteriaBuilder.equal(root.get(BankTransaction_.toBankAccountId), id))
                        .orElse(criteriaBuilder.and())
        );
    }
}
