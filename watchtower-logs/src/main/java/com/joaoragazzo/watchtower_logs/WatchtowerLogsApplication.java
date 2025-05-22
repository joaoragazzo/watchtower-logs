package com.joaoragazzo.watchtower_logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.joaoragazzo.watchtower_logs.security.jwt.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class WatchtowerLogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchtowerLogsApplication.class, args);
	}

}
