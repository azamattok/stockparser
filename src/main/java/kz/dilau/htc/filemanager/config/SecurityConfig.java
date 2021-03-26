package kz.dilau.htc.filemanager.config;

import kz.dilau.htc.filemanager.util.Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Security config.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.keycloak.adapters.springsecurity.management.HttpSessionManager"))
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(request -> corsConfiguration())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(Constants.API_PATH + "/**").authenticated()
                .antMatchers(Constants.OPEN_API_PATH + "/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest().denyAll();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    /**
     * keycloakConfigResolver
     * @return keycloakConfigResolver
     */
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * configure Security.
     * @param auth AuthenticationManagerBuilder
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider authenticationProvider = keycloakAuthenticationProvider();
        authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }
}
