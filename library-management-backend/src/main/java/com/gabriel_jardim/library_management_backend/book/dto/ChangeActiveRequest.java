package com.gabriel_jardim.library_management_backend.book.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeActiveRequest(
    @NotNull(message = "O status do livro é obrigatório.")
    Boolean active
) {}
