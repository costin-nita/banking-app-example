package atm.service.repository.bank;

import atm.service.domain.BankDepositTransaction;
import atm.service.domain.BankDepositTransaction_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class BankDepositTransactionSpecs {

    public static Specification<BankDepositTransaction> byCriteria(long fromAccountId,
                                                                   Optional<Long> toBankAccountId) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                // fromAccountId predicate
                criteriaBuilder.equal(root.get(BankDepositTransaction_.fromAccountId), fromAccountId),
                // toBankAccountId predicate
                toBankAccountId
                        .map(id -> criteriaBuilder.equal(root.get(BankDepositTransaction_.toBankAccountId), id))
                        .orElse(criteriaBuilder.and())
        );
    }
}
