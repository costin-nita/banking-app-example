package atm.client.errors.bank.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class BankTransferTransactionNotFoundException extends Exception {

    private String errorCode = "bank/bank-transfer-transaction/bank-transfer-transaction-not-found";
    private final String criteria;
    private final String value;

    @JsonCreator
    public BankTransferTransactionNotFoundException(@JsonProperty("criteria") String criteria,
                                                    @JsonProperty("value") String value) {

        super(String.format("Bank transfer transaction not found for %s=%s", criteria, value));
        this.criteria = criteria;
        this.value = value;
    }
}
