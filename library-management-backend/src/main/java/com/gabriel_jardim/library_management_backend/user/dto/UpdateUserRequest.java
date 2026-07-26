package com.gabriel_jardim.library_management_backend.user.dto;

import com.gabriel_jardim.library_management_backend.user.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    String name,

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    @Size(max = 150, message = "O e-mail deve ter no maximo 150 caracteres.")
    String email,

    @NotNull(message = "O nível de acesso é obrigatório.")
    UserRole role
) {}
