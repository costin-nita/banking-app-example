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
@Table(name = BaseTable.Bank.BANK_TRANSFER_TRANSACTION, schema = BaseSchema.BANK)
public class BankTransferTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owned_by_account_id", nullable = false, updatable = false)
    protected Account ownedByAccount;

    @Column(name = "owned_by_account_id", updatable = false, insertable = false)
    protected Long ownedByAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_bank_account_id", nullable = false, updatable = false)
    protected BankAccount fromBankAccount;

    @Column(name = "from_bank_account_id", updatable = false, insertable = false)
    protected Long fromBankAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_bank_account_id", nullable = false, updatable = false)
    protected BankAccount toBankAccount;

    @Column(name = "to_bank_account_id", updatable = false, insertable = false)
    protected Long toBankAccountId;

    @Column(name = "amount", nullable = false, updatable = false)
    @Digits(integer = 20, fraction = 5)
    protected BigDecimal amount;

    public BankTransferTransaction(Account ownedByAccount,
                                   BankAccount fromBankAccount,
                                   BankAccount toBankAccount,
                                   BigDecimal amount) {

        this(null, ownedByAccount, fromBankAccount, toBankAccount, amount);
    }

    public BankTransferTransaction(Long id,
                                   Account ownedByAccount,
                                   BankAccount fromBankAccount,
                                   BankAccount toBankAccount,
                                   BigDecimal amount) {

        super(id);
        this.ownedByAccount = ownedByAccount;
        this.ownedByAccountId = ownedByAccount.getId();
        this.fromBankAccount = fromBankAccount;
        this.fromBankAccountId = fromBankAccount.getId();
        this.toBankAccount = toBankAccount;
        this.toBankAccountId = toBankAccount.getId();
        this.amount = amount;
    }
}
