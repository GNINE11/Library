package com.gabriel_jardim.library_management_backend.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel_jardim.library_management_backend.book.BookRepository;
import com.gabriel_jardim.library_management_backend.category.dto.CategoryRequest;
import com.gabriel_jardim.library_management_backend.category.dto.CategoryResponse;
import com.gabriel_jardim.library_management_backend.common.exception.ConflictException;
import com.gabriel_jardim.library_management_backend.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;


    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName()
        );
    }


    public Category findEntityById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
    }


    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        // TODO: Somente admin

        if (categoryRepository.existsByName(request.name())) {
            throw new ConflictException("Categoria já cadastrada.");
        }

        Category category = Category.builder()
            .name(request.name())
            .build();

        return toResponse(categoryRepository.save(category));
    }


    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        // TODO: deve estar logado
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse response = toResponse(category);
            responses.add(response);
        }

        return responses;
    }


    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        // TODO: deve estar logado
        return toResponse(findEntityById(id));
    }


    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        // TODO: somente ADMIN
        Category category = findEntityById(id);

        if (categoryRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new ConflictException("Já existe uma categoria com esse nome.");
        }

        category.setName(request.name());

        return toResponse(category);
    }


    @Transactional
    public void delete(Long id) {
        // TODO: somente admin

        Category category = findEntityById(id);

        if (bookRepository.existsByCategoryId(id)) {
            throw new ConflictException("Não é possivel excluir a categoria, pois existem livros vinculados a ela.");
        }

        categoryRepository.delete(category);
    }
}
