package atm.client.dto.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class BankAccountDecrementAmountRequest {

    private final long id;

    @DecimalMin("1")
    @NotNull
    private final BigDecimal amount;

    @JsonCreator
    public BankAccountDecrementAmountRequest(@JsonProperty("id") long id,
                                             @JsonProperty("amount") BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }
}
