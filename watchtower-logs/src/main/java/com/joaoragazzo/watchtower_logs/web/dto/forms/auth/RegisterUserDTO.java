package com.joaoragazzo.watchtower_logs.web.dto.forms.auth;

import java.time.LocalDate;

import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank(message = AuthenticationErrorCodes.BLANK_USERNAME) @Schema(example = "johndoe") String username,
        @NotBlank(message = AuthenticationErrorCodes.BLANK_NAME) @Schema(example = "John") String name,
        @NotBlank(message = AuthenticationErrorCodes.BLANK_SURNAME) @Schema(example = "Doe") String surname,
        @NotBlank(message = AuthenticationErrorCodes.BLANK_EMAIL) @Email(message = "invalid_email") @Schema(example = "john_doe@email.com") String email,
        @NotBlank(message = AuthenticationErrorCodes.BLANK_PASSWORD) @Size(min = 7, max = 30, message = "invalid_password_size") @Schema(example = "str0ngp4ssw0rd") String password,
        @NotNull(message = AuthenticationErrorCodes.BLANK_BIRTH_DATE) @Schema(example = "2025-05-05") LocalDate birthDate) {
}
