package com.joaoragazzo.watchtower_logs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoragazzo.watchtower_logs.security.handlers.CustomAccessDeniedHandler;
import com.joaoragazzo.watchtower_logs.security.handlers.CustomAuthenticationEntryPointHandler;
import com.joaoragazzo.watchtower_logs.security.jwt.JwtAuthFilter;
import com.joaoragazzo.watchtower_logs.security.jwt.JwtService;
import com.joaoragazzo.watchtower_logs.security.services.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
    private final CustomAuthenticationEntryPointHandler customAuthenticationEntryPoint = new CustomAuthenticationEntryPointHandler();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtService jwtService,
        CustomUserDetailsService customUserDetailsService) throws Exception {
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtService, customUserDetailsService, new ObjectMapper());

        http
            .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint))
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/debug/**").hasRole("USER")
                .anyRequest().permitAll()
                )
            ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
