package com.gabriel_jardim.library_management_backend.author.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(max = 100, message = "O nome do autor deve ter no máximo 100 caracteres.")
    String name
) {}
