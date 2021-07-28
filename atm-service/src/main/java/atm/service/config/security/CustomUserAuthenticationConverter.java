package atm.service.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;

public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) super.extractAuthentication(map);

        if (authentication != null) {
            authentication.setDetails(map.get("account"));
        }

        return authentication;
    }
}
