package com.joaoragazzo.watchtower_logs.web.dto.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Error message returned by the API")
public record ErrorMessageDTO(
        @NotBlank String error) {
}
