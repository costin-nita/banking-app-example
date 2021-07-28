package atm.client.dto.auth.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Builder
public class AccountCreateRequest {

    @Email
    @NotBlank
    @Length(max = 100)
    private final String email;

    @NotBlank
    @Length(max = 300)
    private final String password;

    @NotBlank
    @Length(max = 100)
    private final String firstName;

    @NotBlank
    @Length(max = 100)
    private final String lastName;

    @Length(max = 100)
    private final String middleName;

    @JsonCreator
    public AccountCreateRequest(@JsonProperty("email") String email,
                                @JsonProperty("password") String password,
                                @JsonProperty("firstName") String firstName,
                                @JsonProperty("lastName") String lastName,
                                @JsonProperty("middleName") String middleName) {

        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
