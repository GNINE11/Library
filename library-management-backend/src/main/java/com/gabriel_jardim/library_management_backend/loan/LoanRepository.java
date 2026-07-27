package com.gabriel_jardim.library_management_backend.loan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    boolean existsByBookCopyBookIdAndStatus(Long bookId, LoanStatus status);

    boolean existsByLoanedByIdAndReturnedByIsNull(Long userId);

    boolean existsByReaderIdAndStatus(Long readerId, LoanStatus status);
}
