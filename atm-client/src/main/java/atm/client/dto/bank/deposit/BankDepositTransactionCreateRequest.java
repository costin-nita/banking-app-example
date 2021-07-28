package atm.client.dto.bank.deposit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class BankDepositTransactionCreateRequest {

    @NotNull
    private final long fromAccountId;

    @NotNull
    private final long toBankAccountId;

    @NotNull
    @DecimalMin("1")
    private final BigDecimal amount;

    @JsonCreator
    public BankDepositTransactionCreateRequest(@JsonProperty("fromAccountId") long fromAccountId,
                                               @JsonProperty("toBankAccountId") long toBankAccountId,
                                               @JsonProperty("amount") BigDecimal amount) {

        this.fromAccountId = fromAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
    }
}
