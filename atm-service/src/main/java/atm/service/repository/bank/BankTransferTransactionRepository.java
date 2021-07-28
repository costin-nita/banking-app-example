package atm.service.repository.bank;

import atm.service.domain.BankTransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransferTransactionRepository extends JpaRepository<BankTransferTransaction, Long>, JpaSpecificationExecutor<BankTransferTransaction> {

    BankTransferTransaction findById(long id);
}
