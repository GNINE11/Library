package com.gabriel_jardim.library_management_backend.common.dto;

import java.time.Instant;

public record ErrorResponse(
    Instant timestamp,
    int status,
    String error,
    String message
) {}
