package atm.service.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ConditionalOnProperty(name = "atm.security.cors.enabled", havingValue = "true")
public class CorsFilterConfiguration {

    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        CorsFilter filter = new CorsFilter(source);

        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
