package com.gateway.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF para permitir POST
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/api/v1/usuarios/**").permitAll()
                .anyExchange().permitAll() // Permitir todo el tráfico
            );
        return http.build();
    }
}
