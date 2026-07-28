package com.gabriel_jardim.library_management_backend.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel_jardim.library_management_backend.author.Author;
import com.gabriel_jardim.library_management_backend.author.AuthorService;
import com.gabriel_jardim.library_management_backend.book.dto.BookRequest;
import com.gabriel_jardim.library_management_backend.book.dto.BookResponse;
import com.gabriel_jardim.library_management_backend.book.dto.ChangeActiveRequest;
import com.gabriel_jardim.library_management_backend.book.mapper.BookMapper;
import com.gabriel_jardim.library_management_backend.category.Category;
import com.gabriel_jardim.library_management_backend.category.CategoryService;
import com.gabriel_jardim.library_management_backend.common.exception.BusinessRuleException;
import com.gabriel_jardim.library_management_backend.common.exception.ConflictException;
import com.gabriel_jardim.library_management_backend.common.exception.ResourceNotFoundException;
import com.gabriel_jardim.library_management_backend.loan.LoanRepository;
import com.gabriel_jardim.library_management_backend.loan.LoanStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookMapper bookMapper;


    @Transactional(readOnly = true)
    public Book findEntityById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
    }


    @Transactional
    public BookResponse create(BookRequest request) {
        // TODO: somente ADMIN
        Set<Author> authors = authorService.findAllEntitiesById(request.authorIds());
        Set<Category> categories = categoryService.findAllEntitiesById(request.categoryIds());

        
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new ConflictException("Livro já cadastrado.");
        }

        Book book = Book.builder()
            .title(request.title())
            .isbn(request.isbn())
            .authors(authors)
            .categories(categories)
            .publicationYear(request.publicationYear())
            .description(request.description())
            .coverUrl(request.coverUrl())
            .active(true)
            .build();
        
        return bookMapper.toResponse(bookRepository.save(book));
    }


    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        // TODO: deve estar logado
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responses = new ArrayList<>();

        for (Book book : books) {
            BookResponse response = bookMapper.toResponse(book);
            responses.add(response);
        }

        return responses;
    }


    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        // TODO: deve estar logado
        return bookMapper.toResponse(findEntityById(id));
    }


    @Transactional
    public BookResponse update(Long id, BookRequest request) {

        // TODO: somente admin
        Book book = findEntityById(id);
        Set<Author> authors = authorService.findAllEntitiesById(request.authorIds());
        Set<Category> categories = categoryService.findAllEntitiesById(request.categoryIds());

        if (bookRepository.existsByIsbnAndIdNot(request.isbn(), id)) {
            throw new ConflictException("Já existe um livro cadastrado com esse ISBN.");
        }
        

        book.setTitle(request.title());
        book.setAuthors(authors);
        book.setCategories(categories);
        book.setIsbn(request.isbn());
        book.setPublicationYear(request.publicationYear());
        book.setDescription(request.description());
        book.setCoverUrl(request.coverUrl());

        return bookMapper.toResponse(book);
    }


    @Transactional
    public BookResponse changeActive(Long id, ChangeActiveRequest request) {
        
        // TODO: somente admin
        Book book = findEntityById(id);

        if (!request.active() && loanRepository.existsByBookCopyBookIdAndStatus(id, LoanStatus.ACTIVE)) {
            throw new BusinessRuleException("Não é possível desativar um livro com empréstimos em andamento.");
        }

        book.setActive(request.active());

        return bookMapper.toResponse(book);
    }
}
