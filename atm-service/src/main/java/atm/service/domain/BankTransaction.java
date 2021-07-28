package atm.service.domain;

import atm.service.domain.config.BaseSchema;
import atm.service.domain.config.BaseView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = BaseView.Bank.BANK_TRANSACTION, schema = BaseSchema.BANK)
public class BankTransaction {

    @EmbeddedId
    protected BankTransactionId id;

    @Column(name = "owned_by_account_id", insertable = false, updatable = false)
    protected Long ownedByAccountId;

    @Column(name = "from_account_id", insertable = false, updatable = false)
    protected Long fromAccountId;

    @Column(name = "from_bank_account_id", insertable = false, updatable = false)
    protected Long fromBankAccountId;

    @Column(name = "to_bank_account_id", insertable = false, updatable = false)
    protected Long toBankAccountId;

    @Column(name = "amount", insertable = false, updatable = false)
    @Digits(integer = 20, fraction = 5)
    protected BigDecimal amount;

    @Column(name = "date_created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreated;

    @Column(name = "date_modified", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateModified;
}
