package org.sid.ebankservice.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

// To enable Spring Security
@KeycloakConfiguration
// To enable method security with @PreAuthorize and @PostAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)

// This approach is deprecated on spring boot 3.0.0
// todo: migrate to spring boot 3.0.0
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    // To register Keycloak authentication strategy for public or confidential applications
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // To configure KeycloakAuthenticationProvider with default spring security authentication manager
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // We work with JWT tokens, so we don't need CSRF as we don't use cookies
        http.csrf().disable();
        // To enable H2 console access without authentication
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        // To enable H2 console access without X-Frame-Options header
        http.headers().frameOptions().disable();
        // To enable CORS
        http.authorizeRequests().anyRequest().authenticated();
    }
}
