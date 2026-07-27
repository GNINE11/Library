package com.gabriel_jardim.library_management_backend.reader.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeActiveRequest(
    @NotNull(message = "O status do leitor é obrigatório.")
    Boolean active
) {}
