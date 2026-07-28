package com.gabriel_jardim.library_management_backend.book.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gabriel_jardim.library_management_backend.author.Author;
import com.gabriel_jardim.library_management_backend.author.dto.AuthorResponse;
import com.gabriel_jardim.library_management_backend.author.mapper.AuthorMapper;
import com.gabriel_jardim.library_management_backend.book.Book;
import com.gabriel_jardim.library_management_backend.book.dto.BookResponse;
import com.gabriel_jardim.library_management_backend.category.Category;
import com.gabriel_jardim.library_management_backend.category.dto.CategoryResponse;
import com.gabriel_jardim.library_management_backend.category.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;
    private final CategoryMapper categoryMapper;

    public BookResponse toResponse(Book book) {
        Set<AuthorResponse> authors = new HashSet<>();
        Set<CategoryResponse> categories = new HashSet<>();

        for (Author author : book.getAuthors()) {
            authors.add(authorMapper.toResponse(author));
        }

        for (Category category : book.getCategories()) {
            categories.add(categoryMapper.toResponse(category));
        }

        return new BookResponse(
            book.getId(),
            book.getTitle(),
            authors,
            categories,
            book.getIsbn(),
            book.getPublicationYear(),
            book.getDescription(),
            book.getCoverUrl(),
            book.getActive()
        );
    }
}
