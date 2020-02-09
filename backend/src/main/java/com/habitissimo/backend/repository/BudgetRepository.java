package com.habitissimo.backend.repository;

import com.habitissimo.backend.model.Budget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, Long> {
    Budget findByUserEmail(String email);
    Optional<Budget> findByIdAndUserId(Long id, Long userId);
}
