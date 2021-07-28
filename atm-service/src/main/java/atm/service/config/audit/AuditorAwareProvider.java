package atm.service.config.audit;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

@AllArgsConstructor
public class AuditorAwareProvider implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        @SuppressWarnings("unchecked")
        Long accountId = Long.valueOf(((Map<String, ?>) ((OAuth2Authentication) authentication)
                .getUserAuthentication().getDetails()).get("id").toString());

        return accountId;
    }
}
