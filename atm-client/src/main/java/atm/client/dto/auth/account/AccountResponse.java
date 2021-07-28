package atm.client.dto.auth.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class AccountResponse {

    private final long id;
    private final Date dateCreated;
    private final Date dateModified;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String middleName;

    @JsonCreator
    public AccountResponse(@JsonProperty("id") long id,
                           @JsonProperty("dateCreated") Date dateCreated,
                           @JsonProperty("dateModified") Date dateModified,
                           @JsonProperty("email") String email,
                           @JsonProperty("password") String password,
                           @JsonProperty("firstName") String firstName,
                           @JsonProperty("lastName") String lastName,
                           @JsonProperty("middleName") String middleName) {

        this.id = id;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
