package com.gabriel_jardim.library_management_backend.book.dto;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookRequest(
    @NotBlank(message = "O título do livro é obrigatório.")
    @Size(max = 100, message = "O título do livro deve ter no máximo 100 caracteres.")
    String title,

    @NotNull(message = "É necessário informar pelo menos um autor.")
    @Size(min = 1, message = "É necessário informar pelo menos um autor.")
    Set<@Positive(message = "O identificador do autor deve ser positivo.") Long> authorIds,

    @NotNull(message = "É necessário informar pelo menos uma categoria.")
    @Size(min = 1, message = "É necessário informar pelo menos uma categoria.")
    Set<@Positive(message = "O identificador da categoria deve ser positivo.") Long> categoryIds,

    @NotBlank(message = "O ISBN é obrigatório.")
    @Size(max = 20, message = "O ISBN deve ter no máximo 20 caracteres.")
    String isbn,

    @NotNull(message = "O ano de publicação é obrigatório.")
    @Min(value = 1000, message = "O ano de publicação deve possuir quatro dígitos.")
    @Max(value = 9999, message = "O ano de publicação deve possuir quatro dígitos.")
    Integer publicationYear,

    @Size(max = 1000, message = "A descrição do livro deve ter no máximo 1000 caracteres.")
    String description,

    @URL(message = "O link da capa é inválido.")
    @Size(max = 500, message = "O link da capa deve ter no máximo 500 caracteres.")
    String coverUrl
) {}
