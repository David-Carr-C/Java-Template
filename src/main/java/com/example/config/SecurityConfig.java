package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/api/**").authenticated();
//                    auth.anyRequest().permitAll();

                    auth.requestMatchers("/api/**").permitAll();
                    auth.requestMatchers("/api/user").permitAll();
                    auth.anyRequest().permitAll();
//                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(ALWAYS);
                    session.sessionFixation().migrateSession();
                })
                .build();
    }
}
