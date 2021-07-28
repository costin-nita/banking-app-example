package atm.service.repository.bank;

import atm.service.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, JpaSpecificationExecutor<BankAccount> {

    BankAccount findById(long id);

    List<BankAccount> findByAccountId(long accountId);
}
