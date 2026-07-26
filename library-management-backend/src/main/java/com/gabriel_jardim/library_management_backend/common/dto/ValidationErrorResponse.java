package com.gabriel_jardim.library_management_backend.common.dto;

import java.time.Instant;
import java.util.Map;

public record ValidationErrorResponse(
    Instant timestamp,
    int status,
    String message,
    Map<String, String> errors
) {}
