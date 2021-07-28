package atm.client.dto.bank.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class BankTransferTransactionResponse {

    private final long id;
    private final Date dateCreated;
    private final Date dateModified;
    private final long ownedByAccountId;
    private final long fromBankAccountId;
    private final long toBankAccountId;
    private final BigDecimal amount;

    @JsonCreator
    public BankTransferTransactionResponse(@JsonProperty("id") long id,
                                           @JsonProperty("dateCreated") Date dateCreated,
                                           @JsonProperty("dateModified") Date dateModified,
                                           @JsonProperty("ownedByAccountId") long ownedByAccountId,
                                           @JsonProperty("fromBankAccountId") long fromBankAccountId,
                                           @JsonProperty("toBankAccountId") long toBankAccountId,
                                           @JsonProperty("amount") BigDecimal amount) {

        this.id = id;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.ownedByAccountId = ownedByAccountId;
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
    }
}
