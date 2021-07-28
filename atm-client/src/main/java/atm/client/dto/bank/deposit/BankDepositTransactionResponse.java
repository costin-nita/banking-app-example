package atm.client.dto.bank.deposit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class BankDepositTransactionResponse {

    private final long id;
    private final Date dateCreated;
    private final Date dateModified;
    private final long fromAccountId;
    private final long toBankAccountId;
    private final BigDecimal amount;

    @JsonCreator
    public BankDepositTransactionResponse(@JsonProperty("id") long id,
                                          @JsonProperty("dateCreated") Date dateCreated,
                                          @JsonProperty("dateModified") Date dateModified,
                                          @JsonProperty("fromAccountId") long fromAccountId,
                                          @JsonProperty("toBankAccountId") long toBankAccountId,
                                          @JsonProperty("amount") BigDecimal amount) {

        this.id = id;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.fromAccountId = fromAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
    }
}
