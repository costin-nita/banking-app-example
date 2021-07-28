package atm.client.dto.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BankAccountCreateRequest {

    @NotNull
    private final long accountId;

    @JsonCreator
    public BankAccountCreateRequest(@JsonProperty("accountId") long accountId) {
        this.accountId = accountId;
    }
}
