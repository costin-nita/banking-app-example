package atm.service.repository.bank;

import atm.service.domain.BankTransaction;
import atm.service.domain.BankTransactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, BankTransactionId>, JpaSpecificationExecutor<BankTransaction> {
}
