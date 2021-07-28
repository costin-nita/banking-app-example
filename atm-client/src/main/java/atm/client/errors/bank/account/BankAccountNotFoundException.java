package atm.client.errors.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class BankAccountNotFoundException extends Exception {

    private final String errorCode = "bank/bank-account/bank-account-not-found";
    private final String criteria;
    private final String value;

    @JsonCreator
    public BankAccountNotFoundException(@JsonProperty("criteria") String criteria,
                                        @JsonProperty("value") String value) {

        super(String.format("Bank account not found for %s=%s", criteria, value));
        this.criteria = criteria;
        this.value = value;
    }
}
