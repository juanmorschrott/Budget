package com.habitissimo.backend.service;

import com.habitissimo.backend.dto.BudgetDTO;
import com.habitissimo.backend.model.Budget;
import com.habitissimo.backend.model.State;
import com.habitissimo.backend.model.User;
import com.habitissimo.backend.repository.BudgetRepository;
import com.habitissimo.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public List<Budget> getAll(BudgetDTO budgetDTO) {
        if (budgetDTO.getEmail() != null && !budgetDTO.getEmail().isEmpty()) {

        }
        return null;
    }

    @Override
    public Budget createBudget(BudgetDTO budgetDTO) {
        User user = userRepository.findByEmail(budgetDTO.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(budgetDTO.getEmail());
        }
        user.setPhone(budgetDTO.getPhone());
        user.setAddress(budgetDTO.getAddress());

        user = userRepository.save(user);

        Budget budget = new Budget();
        budget.setTitle(budgetDTO.getTitle());
        budget.setDescription(budgetDTO.getDescription());
        budget.setCategory(budgetDTO.getCategory());
        budget.setState(State.PENDING);
        budget.setUser(user);

        return budgetRepository.save(budget);

    }

    @Override
    public Budget updateBudget(Long id, BudgetDTO budgetDTO) throws Exception {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isPresent() && budget.get().getState().equals(State.PENDING)) {
            Budget b = budget.get();
            b.setTitle(budgetDTO.getTitle());
            b.setDescription(budgetDTO.getDescription());
            b.setCategory(budgetDTO.getCategory());
            return budgetRepository.save(b);

        } else {
            throw new Exception("Budget doesn´t exist or is not PENDING");
        }

    }

    @Override
    public Budget publishPendingBudget(Long id, BudgetDTO budgetDTO) throws Exception {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isPresent() && budget.get().getState().equals(State.PENDING) &&
                !budget.get().getTitle().isEmpty() && !budget.get().getCategory().isEmpty()) {
            budget.get().setState(State.PUBLISHED);
            return budgetRepository.save(budget.get());
        } else {
            throw new Exception("Budget doesn´t exist or is not PENDING");
        }
    }

    @Override
    public Budget discardBudget(Long id, BudgetDTO budgetDTO) throws Exception {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isPresent() && (budget.get().getState().equals(State.PENDING) || budget.get().getState().equals(State.PUBLISHED))) {
            budget.get().setState(State.DISCARTED);
            return budgetRepository.save(budget.get());
        } else {
            throw new Exception("Budget doesn´t exist or is not PENDING");
        }
    }

    @Override
    public Budget suggestCategory(BudgetDTO budgetDTO) {
        if (budgetDTO.getCategory() != null && !budgetDTO.getCategory().isEmpty()) {
            String[] tokens = budgetDTO.getCategory().split(" ");

        }

        return null;
    }

}
