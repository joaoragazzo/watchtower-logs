package com.joaoragazzo.watchtower_logs.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "WatchTower Logs", 
        version = "1.0", 
        description = "Project for Springboot, JWT, logging and security studies", 
        contact = @Contact(name = "João Ragazzo")),  
    security = {@SecurityRequirement(name = "Cookie")}
    )
@SecuritySchemes({
        @SecurityScheme(name = "Cookie", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "Cookie")
})
public class SwaggerOpenAPIConfig {

}
