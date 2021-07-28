package atm.client.errors.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class BankAccountDecrementAboveLimitException extends Exception {

    private final String errorCode = "bank/bank-account/bank-account-decrement-above-limit-exception";
    private final long bankAccountId;
    private final BigDecimal availableAmount;
    private final BigDecimal decrementAmount;

    @JsonCreator
    public BankAccountDecrementAboveLimitException(@JsonProperty("bankAccountId") long bankAccountId,
                                                   @JsonProperty("availableAmount") BigDecimal availableAmount,
                                                   @JsonProperty("decrementAmount") BigDecimal decrementAmount) {

        super(String.format("Bank account amount cannot be decremented above its limit. Available: %.5f, Decrement: %.5f",
                availableAmount, decrementAmount));

        this.bankAccountId = bankAccountId;
        this.availableAmount = availableAmount;
        this.decrementAmount = decrementAmount;
    }
}
