package atm.service.service;

import atm.client.dto.auth.account.AccountResponse;
import atm.client.errors.auth.account.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountResponse user;

        try {
            user = accountService.findByEmail(username, true);
        } catch (AccountNotFoundException ex) {
            throw new UsernameNotFoundException(
                    String.format("User was not found for username='%s'", username), ex
            );
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
