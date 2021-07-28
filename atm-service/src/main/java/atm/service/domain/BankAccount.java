package atm.service.domain;

import atm.service.domain.config.BaseEntity;
import atm.service.domain.config.BaseSchema;
import atm.service.domain.config.BaseTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = BaseTable.Bank.BANK_ACCOUNT, schema = BaseSchema.BANK)
public class BankAccount extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    protected Account account;

    @Column(name = "account_id", updatable = false, insertable = false)
    @Setter(AccessLevel.NONE)
    protected Long accountId;

    @Column(name = "amount", nullable = false)
    @Digits(integer = 20, fraction = 5)
    protected BigDecimal amount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toBankAccount")
    @Setter(AccessLevel.NONE)
    protected Set<BankDepositTransaction> bankDepositTransactionsTo = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromBankAccount")
    @Setter(AccessLevel.NONE)
    protected Set<BankTransferTransaction> bankTransferTransactionsFrom = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toBankAccount")
    @Setter(AccessLevel.NONE)
    protected Set<BankTransferTransaction> bankTransferTransactionsTo = new HashSet<>();

    public BankAccount(Account account, BigDecimal amount) {
        this(null, account, amount);
    }

    public BankAccount(Long id, Account account, BigDecimal amount) {
        super(id);
        this.account = account;
        this.accountId = account.getId();
        this.amount = amount;
    }
}
