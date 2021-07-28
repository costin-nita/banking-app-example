package atm.service.config;

import atm.service.config.audit.AuditorAwareProvider;
import atm.service.config.audit.DateTimeAuditProvider;
import atm.service.repository.auth.AccountRepository;
import atm.service.repository.auth.AuthRepositories;
import atm.service.repository.bank.BankRepositories;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = {AuthRepositories.class, BankRepositories.class}
)
@EnableTransactionManagement
@EnableJpaAuditing(
        auditorAwareRef = "auditorProvider",
        dateTimeProviderRef = "dateAuditorProvider",
        setDates = true,
        modifyOnCreate = true
)
@AllArgsConstructor
public class DatabaseConfiguration {

    private final AccountRepository accountRepository;

    @Bean("auditorProvider")
    public AuditorAware<Long> accountAuditorProvider() {
        return new AuditorAwareProvider();
    }

    @Bean("dateAuditorProvider")
    public DateTimeProvider dateAuditorProvider() {
        return new DateTimeAuditProvider();
    }
}
