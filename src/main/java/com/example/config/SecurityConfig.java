package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {

                    auth.requestMatchers("/api/**").permitAll();
                    auth.requestMatchers("/api/user").permitAll();
                    auth.anyRequest().permitAll();

//                    auth.requestMatchers("/api/public/**").permitAll();
//                    auth.requestMatchers("/api/**").authenticated();
//                    auth.requestMatchers("/api/user").authenticated();
//                    auth.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable) // deshabilita CSRF (Cross-Site Request Forgery)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(ALWAYS);
                    session.sessionFixation().migrateSession();
                })
                .build();
    }
}

/**
 * Se deshabilita CSRF (Cross-Site Request Forgery)
 * para que acepte peticiones desde cualquier origen y admita post, put y delete en Postman.
 *
 * Hay que tener ne cuenta que GET no se ve afectado por CSRF.
 */