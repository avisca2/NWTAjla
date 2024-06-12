package com.medical.securityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
     protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.formLogin(login->{
            login.disable();
        }).csrf(crsf ->{
            crsf.disable();
        }).cors(cors -> {
            cors.disable();
        }).authorizeHttpRequests(req -> {
                req.requestMatchers("/**").permitAll().anyRequest().authenticated();
            })
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            });
        return http.build();
    }
}
