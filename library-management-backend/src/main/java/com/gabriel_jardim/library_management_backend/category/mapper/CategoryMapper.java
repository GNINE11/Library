package com.gabriel_jardim.library_management_backend.category.mapper;

import com.gabriel_jardim.library_management_backend.category.Category;
import com.gabriel_jardim.library_management_backend.category.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
