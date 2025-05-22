package com.joaoragazzo.watchtower_logs.web.dto.forms.auth;

import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(
    @NotBlank(message = AuthenticationErrorCodes.BLANK_USERNAME) 
    @Schema(example = "johndoe") 
    String username,
    
    @NotBlank(message = AuthenticationErrorCodes.BLANK_PASSWORD) 
    @Schema(example = "str0ngp4ssw0rd") 
    String password
) {}
