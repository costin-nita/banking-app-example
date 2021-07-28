package atm.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class BankTransactionId implements Serializable {

    @Column(name = "transaction_type", insertable = false, updatable = false)
    protected String transactionType;

    @Column(name = "transaction_id", insertable = false, updatable = false)
    protected Long transactionId;
}
