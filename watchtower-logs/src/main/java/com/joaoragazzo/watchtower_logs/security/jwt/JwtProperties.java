package com.joaoragazzo.watchtower_logs.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "jwt")
@Configuration
@Getter
@Setter
public class JwtProperties {
    private String secretKey;
    private Long expirationMs;
}
