package com.gateway.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            
            .csrf(csrf -> csrf.disable()) 
            
            .cors(cors -> cors.configurationSource(request -> 
                new CorsConfiguration().applyPermitDefaultValues()
            ))
            
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            
            .authorizeExchange(exchange -> exchange
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .pathMatchers("/api/usuarios/login", "/api/usuarios/registrar").permitAll()   
                .pathMatchers("/api/*/usuarios/login", "/api/*/usuarios/registrar").permitAll() 
                .pathMatchers("/api/notificaciones/**").permitAll()
                .pathMatchers("/api/alertas/**").permitAll()
                .anyExchange().authenticated() 
            );
        return http.build();
    }

}
