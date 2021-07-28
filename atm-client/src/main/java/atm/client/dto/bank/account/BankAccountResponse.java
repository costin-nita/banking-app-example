package atm.client.dto.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class BankAccountResponse {

    private final long id;
    private final Date dateCreated;
    private final Date dateModified;
    private final long accountId;
    private final BigDecimal amount;

    @JsonCreator
    public BankAccountResponse(@JsonProperty("id") long id,
                               @JsonProperty("dateCreated") Date dateCreated,
                               @JsonProperty("dateModified") Date dateModified,
                               @JsonProperty("accountId") long accountId,
                               @JsonProperty("amount") BigDecimal amount) {

        this.id = id;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.accountId = accountId;
        this.amount = amount;
    }
}
