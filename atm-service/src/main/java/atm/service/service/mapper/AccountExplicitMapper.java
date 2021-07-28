package atm.service.service.mapper;

import atm.client.dto.auth.account.AccountCreateRequest;
import atm.client.dto.auth.account.AccountResponse;
import atm.service.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountExplicitMapper implements AccountMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponse createResponseFromEntity(Account entity, boolean includePassword) {
        return new AccountResponse(
                entity.getId(),
                entity.getDateCreated(),
                entity.getDateModified(),
                entity.getEmail(),
                includePassword ? entity.getPassword() : null,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName()
        );
    }

    @Override
    public Account createEntityFromCreateRequest(AccountCreateRequest request) {

        return new Account(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getMiddleName()
        );
    }
}
