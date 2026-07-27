package com.gabriel_jardim.library_management_backend.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 25, message = "O nome da categoria deve ter no máximo 25 caracteres.")
    String name
) {}
