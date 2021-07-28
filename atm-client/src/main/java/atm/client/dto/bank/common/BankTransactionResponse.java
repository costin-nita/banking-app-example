package atm.client.dto.bank.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class BankTransactionResponse {

    private final String transactionType;
    private final long transactionId;
    private final long ownedByAccountId;
    private final Long fromAccountId;
    private final Long fromBankAccountId;
    private final long toBankAccountId;
    private final BigDecimal amount;
    private final Date dateCreated;
    private final Date dateModified;

    @JsonCreator
    public BankTransactionResponse(@JsonProperty("transactionType") String transactionType,
                                   @JsonProperty("transactionId") long transactionId,
                                   @JsonProperty("ownedByAccountId") long ownedByAccountId,
                                   @JsonProperty("fromAccountId") Long fromAccountId,
                                   @JsonProperty("fromBankAccountId") Long fromBankAccountId,
                                   @JsonProperty("toBankAccountId") long toBankAccountId,
                                   @JsonProperty("amount") BigDecimal amount,
                                   @JsonProperty("dateCreated") Date dateCreated,
                                   @JsonProperty("dateModified") Date dateModified) {

        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.ownedByAccountId = ownedByAccountId;
        this.fromAccountId = fromAccountId;
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
}
