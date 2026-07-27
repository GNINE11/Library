package com.gabriel_jardim.library_management_backend.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
