package com.gabriel_jardim.library_management_backend.user.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeActiveRequest(
    @NotNull(message = "O status do usuário é obrigatório.")
    Boolean active
) {}
