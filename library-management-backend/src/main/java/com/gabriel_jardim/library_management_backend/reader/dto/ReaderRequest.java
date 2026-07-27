package com.gabriel_jardim.library_management_backend.reader.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ReaderRequest(

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    String name,

    @NotBlank(message = "O cpf é obrigatório.")
    @CPF(message = "CPF inválido.")
    String cpf,

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    @Size(max = 150, message = "O e-mail deve ter no maximo 150 caracteres.")
    String email,

    @Pattern(
        regexp = "^(\\(\\d{2}\\)\\s?)?(9?\\d{4}-?\\d{4})$|^\\d{10,11}$",
        message = "Telefone inválido."
    )
    String phone
) {}
