package atm.service.domain;

import atm.service.domain.config.BaseEntity;
import atm.service.domain.config.BaseSchema;
import atm.service.domain.config.BaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = BaseTable.Bank.BANK_DEPOSIT_TRANSACTION, schema = BaseSchema.BANK)
public class BankDepositTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id", nullable = false, updatable = false)
    protected Account fromAccount;

    @Column(name = "from_account_id", updatable = false, insertable = false)
    protected Long fromAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_bank_account_id", nullable = false, updatable = false)
    protected BankAccount toBankAccount;

    @Column(name = "to_bank_account_id", updatable = false, insertable = false)
    protected Long toBankAccountId;

    @Column(name = "amount", nullable = false, updatable = false)
    @Digits(integer = 20, fraction = 5)
    protected BigDecimal amount;

    public BankDepositTransaction(Account fromAccount,
                                  BankAccount toBankAccount,
                                  BigDecimal amount) {

        this(null, fromAccount, toBankAccount, amount);
    }

    public BankDepositTransaction(Long id,
                                  Account fromAccount,
                                  BankAccount toBankAccount,
                                  BigDecimal amount) {

        super(id);
        this.fromAccount = fromAccount;
        this.fromAccountId = fromAccount.getId();
        this.toBankAccount = toBankAccount;
        this.toBankAccountId = toBankAccount.getId();
        this.amount = amount;
    }
}
