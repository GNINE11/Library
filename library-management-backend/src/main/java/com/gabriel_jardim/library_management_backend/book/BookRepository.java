package com.gabriel_jardim.library_management_backend.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    boolean existsByIsbnAndIdNot(String isbn, Long id);
    
    boolean existsByAuthorsId(Long authorId);
    
    boolean existsByCategoriesId(Long categoryId);
}
