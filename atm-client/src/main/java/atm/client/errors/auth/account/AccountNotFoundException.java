package atm.client.errors.auth.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class AccountNotFoundException extends Exception {

    private final String errorCode = "auth/account/account-not-found";
    private final String criteria;
    private final String value;

    @JsonCreator
    public AccountNotFoundException(@JsonProperty("criteria") String criteria,
                                    @JsonProperty("value") String value) {
        super(String.format("Account not found for %s=%s", criteria, value));
        this.criteria = criteria;
        this.value = value;
    }
}
