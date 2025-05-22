package com.joaoragazzo.watchtower_logs.web.dto.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Success message returned by the API")
public record SuccessMessageDTO(
    @NotBlank String success
) {}
