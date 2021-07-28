package atm.service.repository.bank;

import atm.service.domain.BankTransferTransaction;
import atm.service.domain.BankTransferTransaction_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class BankTransferTransactionSpecs {

    public static Specification<BankTransferTransaction> byCriteria(long accountId,
                                                                    Optional<Long> fromBankAccountId,
                                                                    Optional<Long> toBankAccountId) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                // ownedByAccountId condition
                criteriaBuilder.equal(root.get(BankTransferTransaction_.ownedByAccountId), accountId),
                // fromBankAccountId condition
                fromBankAccountId
                        .map(id -> criteriaBuilder.equal(root.get(BankTransferTransaction_.fromBankAccountId), id))
                        .orElse(criteriaBuilder.and()),
                // toBankAccountId condition
                toBankAccountId
                        .map(id -> criteriaBuilder.equal(root.get(BankTransferTransaction_.toBankAccountId), id))
                        .orElse(criteriaBuilder.and())
        );
    }
}
