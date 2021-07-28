package atm.service.config.security;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "atm.security.oauth2.jwt")
public class OAuth2JwtProperties {

    @NotBlank
    private String signingKey;
}
