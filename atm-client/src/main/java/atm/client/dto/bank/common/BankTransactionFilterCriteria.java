package atm.client.dto.bank.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BankTransactionFilterCriteria {

    private Long ownedByAccountId;
    private Long fromAccountId;
    private Long fromBankAccountId;
    private Long toBankAccountId;

    @JsonCreator
    public BankTransactionFilterCriteria(@JsonProperty("ownedByAccountId") Long ownedByAccountId,
                                         @JsonProperty("fromAccountId") Long fromAccountId,
                                         @JsonProperty("fromBankAccountId") Long fromBankAccountId,
                                         @JsonProperty("toBankAccountId") Long toBankAccountId) {

        this.ownedByAccountId = ownedByAccountId;
        this.fromAccountId = fromAccountId;
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
    }
}
