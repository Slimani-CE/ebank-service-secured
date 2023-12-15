package org.sid.ebankservice.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;

@KeycloakConfiguration
public class KeycloakAdapterConfig {
    // To retrieve Keycloak configuration from application.properties
    @Bean
    KeycloakSpringBootConfigResolver springBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
