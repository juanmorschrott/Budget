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
        // I would implement a call to an ElasticSearch service:
        // GET /habitissimo/categories/_search
        // {
        //      "query": {
        //          "constant_score": {
        //              "filter": {
        //                  "term": {
        //                      "category_values": “quiero quitar la bañera y poner una mampara con un plato de ducha"
        //                  }
        //              }
        //          }
        //      }
        //}
    }

}
