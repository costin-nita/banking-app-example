package atm.client.errors.bank.deposit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class BankDepositTransactionNotFoundException extends Exception {

    private final String errorCode = "bank/bank-deposit-transaction/bank-deposit-transaction-not-found";
    private final String criteria;
    private final String value;

    @JsonCreator
    public BankDepositTransactionNotFoundException(@JsonProperty("criteria") String criteria,
                                                   @JsonProperty("value") String value) {
        super(String.format("Bank deposit transaction not found for %s=%s", criteria, value));
        this.criteria = criteria;
        this.value = value;
    }
}
