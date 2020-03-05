package com.habitissimo.backend.repository;

import com.habitissimo.backend.model.Budget;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends PagingAndSortingRepository<Budget, Long> {
    Optional<Budget> findByIdAndUserId(Long id, Long userId);
}
