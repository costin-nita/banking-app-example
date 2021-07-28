package atm.service.repository.bank;

import atm.service.domain.BankDepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDepositTransactionRepository extends JpaRepository<BankDepositTransaction, Long>, JpaSpecificationExecutor<BankDepositTransaction> {

    BankDepositTransaction findById(long id);
}
