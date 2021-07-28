package atm.service.config.security;

import atm.client.dto.auth.account.AccountResponse;
import atm.service.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import pl.touk.throwing.ThrowingBiFunction;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomJwtAccessTokenEnhancer implements TokenEnhancer {

    private final AccountService accountService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        String email = authentication.getName();

        Map<String, Object> additionalInfo = new HashMap<>();

        AccountResponse accountResponse = ThrowingBiFunction.unchecked(accountService::findByEmail).apply(email, false);
        additionalInfo.put("account", accountResponse);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
