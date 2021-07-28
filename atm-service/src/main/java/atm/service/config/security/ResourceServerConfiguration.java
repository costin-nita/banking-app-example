package atm.service.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final TokenStore jwtTokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources
                .tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/account").permitAll()
                .anyRequest().authenticated();
    }
}
