package atm.client.errors.bank.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class BankTransferOnSameAccountException extends Exception {

    private final String errorCode = "bank/bank-transfer-transaction/bank-transfer-on-same-account";
    private final long bankAccountId;

    @JsonCreator
    public BankTransferOnSameAccountException(@JsonProperty("bankAccountId") long bankAccountId) {
        super(String.format("Cannot transfer to the same bank account. ID=%d", bankAccountId));
        this.bankAccountId = bankAccountId;
    }
}
