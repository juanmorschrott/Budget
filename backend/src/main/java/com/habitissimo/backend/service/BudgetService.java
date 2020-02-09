package com.habitissimo.backend.service;

import com.habitissimo.backend.dto.BudgetDTO;
import com.habitissimo.backend.model.Budget;

import java.util.List;

public interface BudgetService {

    List<Budget> getAll(BudgetDTO budgetDTO);

    Budget createBudget(BudgetDTO budgetDTO);

    Budget updateBudget(Long id, BudgetDTO budgetDTO) throws Exception;

    Budget publishPendingBudget(Long id, BudgetDTO budgetDTO) throws Exception;

    Budget discardBudget(Long id, BudgetDTO budgetDTO) throws Exception;

    Budget suggestCategory(Long id);

}
