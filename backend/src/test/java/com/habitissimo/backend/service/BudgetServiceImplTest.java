package com.habitissimo.backend.service;

import com.habitissimo.backend.dto.BudgetDTO;
import com.habitissimo.backend.model.Budget;
import com.habitissimo.backend.model.State;
import com.habitissimo.backend.model.User;
import com.habitissimo.backend.repository.BudgetRepository;
import com.habitissimo.backend.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BudgetServiceImplTest {

    @InjectMocks
    private BudgetServiceImpl budgetService;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private UserRepository userRepository;

    private User mockUser;
    private BudgetDTO mockBudgetDTO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createBudgetTest_should_find_user() {
        // given
        BudgetDTO budgetDTO = generateMockBudgetDTO();

        when(userRepository.findByEmail(budgetDTO.getEmail())).thenReturn(generateMockUser());
        when(userRepository.save(any())).thenReturn(generateMockUser());
        when(budgetRepository.save(any())).thenReturn(generateMockBudget());

        // when
        Budget budget = budgetService.createBudget(budgetDTO);

        // then
        assertThat(budget).isNotNull();
        assertThat(budget.getUser().getEmail()).isEqualTo(budgetDTO.getEmail());
    }

    @Test
    public void updateBudgetTest_should_update_budget() throws Exception {
        // given
        BudgetDTO budgetDTO = generateMockBudgetDTO();

        when(budgetRepository.findById(1L)).thenReturn(Optional.of(generateMockBudget()));
        when(budgetRepository.save(any())).thenReturn(generateMockBudget());

        // when
        Budget result = budgetService.updateBudget(1L, budgetDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(budgetDTO.getTitle());
        assertThat(result.getDescription()).isEqualTo(budgetDTO.getDescription());
        assertThat(result.getCategory()).isEqualTo(budgetDTO.getCategory());
    }

    @Test
    public void publishPendingBudgetTest_should_not_change_to_published() throws Exception {
        // given
        BudgetDTO budgetDTO = generateMockBudgetDTO();
        budgetDTO.setTitle("");
        Budget budget = generateMockBudget();
        budget.setState(State.PENDING);

        when(budgetRepository.findById(1L)).thenReturn(Optional.of(generateMockBudget()));
        when(budgetRepository.save(any())).thenReturn(budget);

        // when
        Budget result = budgetService.publishPendingBudget(1L, budgetDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getState()).isNotEqualTo(State.PUBLISHED);
    }

    @Test
    public void publishPendingBudgetTest_should_change_to_published() throws Exception {
        // given
        BudgetDTO budgetDTO = generateMockBudgetDTO();
        Budget budget = generateMockBudget();
        budget.setState(State.PUBLISHED);

        when(budgetRepository.findById(1L)).thenReturn(Optional.of(generateMockBudget()));
        when(budgetRepository.save(any())).thenReturn(budget);

        // when
        Budget result = budgetService.publishPendingBudget(1L, budgetDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getState()).isEqualTo(State.PUBLISHED);
        verify(budgetRepository, times(1)).save(any());
    }

    private BudgetDTO generateMockBudgetDTO() {
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setTitle("Title");
        budgetDTO.setDescription("Description");
        budgetDTO.setCategory("Category");
        budgetDTO.setEmail("Email@mail.com");
        budgetDTO.setPhone("654321234");
        budgetDTO.setAddress("Address X nº 13");

        return budgetDTO;
    }

    private User generateMockUser() {
        User user = new User();
        user.setEmail("Email@mail.com");
        user.setEmail("Email@mail.com");
        user.setPhone("654321234");

        return user;
    }

    private Budget generateMockBudget() {
        Budget budget = new Budget();
        budget.setId(1L);
        budget.setTitle("Title");
        budget.setDescription("Description");
        budget.setCategory("Category");
        budget.setUser(generateMockUser());
        budget.setState(State.PENDING);

        return budget;
    }

}
