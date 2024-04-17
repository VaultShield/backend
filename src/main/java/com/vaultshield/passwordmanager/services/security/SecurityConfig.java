package com.vaultshield.passwordmanager.services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable())  // CSRF !!
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/auth/register" , "/api/auth/login", "/api/auth/verifysession").permitAll() // to improve!!
    //             .anyRequest().authenticated())
    //         .httpBasic(httpBasic -> httpBasic.realmName("api"));

    //     return http.build();
    // }

}
