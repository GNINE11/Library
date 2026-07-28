package com.gabriel_jardim.library_management_backend.book.dto;

import java.util.Set;

import com.gabriel_jardim.library_management_backend.author.dto.AuthorResponse;
import com.gabriel_jardim.library_management_backend.category.dto.CategoryResponse;

public record BookResponse(
    Long id,
    String title,
    Set<AuthorResponse> authors,
    Set<CategoryResponse> categories,
    String isbn,
    Integer publicationYear,
    String description,
    String coverUrl,
    Boolean active
) {}
