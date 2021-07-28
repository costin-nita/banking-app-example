package atm.client.dto.bank.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
public class BankTransferTransactionCreateRequest {

    private final long fromBankAccountId;

    private final long toBankAccountId;

    @DecimalMin("1")
    private final BigDecimal amount;

    @JsonCreator
    public BankTransferTransactionCreateRequest(@JsonProperty("fromBankAccountId") long fromBankAccountId,
                                                @JsonProperty("toBankAccountId") long toBankAccountId,
                                                @JsonProperty("amount") BigDecimal amount) {

        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
    }
}
