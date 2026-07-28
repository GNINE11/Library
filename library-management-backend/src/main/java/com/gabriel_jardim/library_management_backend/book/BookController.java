package com.gabriel_jardim.library_management_backend.book;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel_jardim.library_management_backend.book.dto.BookRequest;
import com.gabriel_jardim.library_management_backend.book.dto.BookResponse;
import com.gabriel_jardim.library_management_backend.book.dto.ChangeActiveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    
    private final BookService bookService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookRequest request) {
        return bookService.create(request);
    }

    
    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }


    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return bookService.update(id, request);
    }


    @PatchMapping("/{id}")
    public BookResponse changeActive(@PathVariable Long id, @Valid @RequestBody ChangeActiveRequest request) {
        return bookService.changeActive(id, request);
    }
}
