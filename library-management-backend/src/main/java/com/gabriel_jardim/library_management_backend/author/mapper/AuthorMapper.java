package com.gabriel_jardim.library_management_backend.author.mapper;

import com.gabriel_jardim.library_management_backend.author.Author;
import com.gabriel_jardim.library_management_backend.author.dto.AuthorResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponse toResponse(Author author) {
        return new AuthorResponse(author.getId(), author.getName());
    }
}
