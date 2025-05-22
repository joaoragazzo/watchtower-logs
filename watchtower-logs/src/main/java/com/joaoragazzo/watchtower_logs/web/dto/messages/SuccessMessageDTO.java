package com.joaoragazzo.watchtower_logs.web.dto.messages;

import jakarta.validation.constraints.NotBlank;

public record SuccessMessageDTO(
    @NotBlank String success
) {}
