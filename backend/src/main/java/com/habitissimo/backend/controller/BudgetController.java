package com.habitissimo.backend.controller;

import com.habitissimo.backend.dto.BudgetDTO;
import com.habitissimo.backend.model.Budget;
import com.habitissimo.backend.repository.BudgetRepository;
import com.habitissimo.backend.service.BudgetServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path="/api/v1/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetServiceImpl budgetService;

    @ApiOperation(value = "View a list of available Budgets", response = ResponseEntity.class)
    @GetMapping
    public List<Budget> getAllBudgets() {
        return (List<Budget>) budgetRepository.findAll();
    }

    @ApiOperation(value = "Creates a new Budget", response = ResponseEntity.class)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Budget createBudget(@Valid @RequestBody BudgetDTO budgetDTO) {
        return budgetService.createBudget(budgetDTO);
    }

    @ApiOperation(value = "Set Budget as PUBLISHED", response = ResponseEntity.class)
    @PostMapping(value = "/{id}/publish", consumes = "application/json", produces = "application/json")
    public Budget publishBudget(@PathVariable Long id, @Valid @RequestBody BudgetDTO budgetDTO) throws Exception {
        return budgetService.publishPendingBudget(id, budgetDTO);
    }

    @ApiOperation(value = "Set Budget as DISCARTED", response = ResponseEntity.class)
    @PostMapping(value = "/{id}/discard", consumes = "application/json", produces = "application/json")
    public Budget discardBudget(@PathVariable Long id, @Valid  @RequestBody BudgetDTO budgetDTO) throws Exception {
        return budgetService.discardBudget(id, budgetDTO);
    }

    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @Valid  @RequestBody BudgetDTO budgetDTO) throws Exception {
        return budgetService.updateBudget(id, budgetDTO);
    }

    @ApiOperation(value = "Suggest Category", response = ResponseEntity.class)
    @PostMapping(value = "/suggest", consumes = "application/json", produces = "application/json")
    public void suggestCategory( @Valid  @RequestBody BudgetDTO budgetDTO) {
        // 1. We need an ElastiSearch service where we can add the needed indexes.
        // 2. All these indexes can be filled with the information included in every Budget creation request.
        //    Every time somebody adds a Description and selects a Category, we can associate these words
        //    to a category e.g.:
        //        Category:    Reforma baño
        //        Description: Quiero quitar la bañera y poner una mampara con un plato de ducha
        // 3. When our indexes have enough data, we can make use of a full text call:
        // GET /habitissimo/categories/_search
        // {
        //      "query": {
        //          "match": {
        //              "category": {
        //                  "query": “Quiero quitar la bañera y poner una mampara con un plato de ducha",
        //                  "minimum_should_match": 75%
        //              }
        //          }
        //      }
        //}
        //4. If a minimum match percentage is accomplished, then we can return the recommended category.
    }

}
