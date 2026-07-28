package com.gabriel_jardim.library_management_backend.author;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel_jardim.library_management_backend.author.dto.AuthorRequest;
import com.gabriel_jardim.library_management_backend.author.dto.AuthorResponse;
import com.gabriel_jardim.library_management_backend.author.mapper.AuthorMapper;
import com.gabriel_jardim.library_management_backend.book.BookRepository;
import com.gabriel_jardim.library_management_backend.common.exception.ConflictException;
import com.gabriel_jardim.library_management_backend.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    public Author findEntityById(Long id) {
        return authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado."));
    }


    @Transactional(readOnly = true)
    public Set<Author> findAllEntitiesById(Set<Long> ids) {
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(ids));
        
        if (authors.size() != ids.size()) {
            throw new ResourceNotFoundException("Um ou mais autores não foram encontrados.");
        }

        return authors;
    }


    @Transactional
    public AuthorResponse create(AuthorRequest request){

        // TODO: somente ADMIN
        if (authorRepository.existsByName(request.name())) {
            throw new ConflictException("Autor já cadastrado.");
        }

        Author author = Author.builder()
            .name(request.name())
            .build();

        return authorMapper.toResponse(authorRepository.save(author));
    }


    @Transactional(readOnly = true)
    public List<AuthorResponse> findAll() {

        // TODO: deve estar logado

        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> responses = new ArrayList<>();

        for (Author author : authors) {
            AuthorResponse response = authorMapper.toResponse(author);
            responses.add(response);
        }

        return responses;
    }


    @Transactional(readOnly = true)
    public AuthorResponse findById(Long id) {
        // TODO: deve estar logado
        return authorMapper.toResponse(findEntityById(id));
    }


    @Transactional
    public AuthorResponse update(Long id, AuthorRequest request) {
        // TODO: somente ADMIN

        Author author = findEntityById(id);
        
        if (authorRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new ConflictException("Já existe um autor com esse nome.");
        }

        author.setName(request.name());

        return authorMapper.toResponse(author);
    }


    @Transactional
    public void delete(Long id) {
        // TODO: somente ADMIN

        Author author = findEntityById(id);

        if (bookRepository.existsByAuthorsId(id)) {
            throw new ConflictException("Não é possivel excluir o autor, pois existem livros vinculados a ele.");
        }

        authorRepository.delete(author);
    }
}
