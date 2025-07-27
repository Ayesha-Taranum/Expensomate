package com.expensomate.controller;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensomate.dto.BudgetDto;
import com.expensomate.entity.Budget;
import com.expensomate.service.impl.BudgetServiceImpl;
import com.expensomate.template.ResponseTemplate; 
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RequestMapping("budgets")
@RestController
@AllArgsConstructor
@Slf4j
public class BudgetController {
	
	private BudgetServiceImpl budgetService;
	
	@GetMapping
	public ResponseEntity<List<Budget>> getBudgets() {
		List<Budget> list = this.budgetService.getAllBudgets();
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/budget")
	public ResponseEntity<Budget> getBudget(@RequestParam("budgetId") String budgetId) {
		Budget budget = this.budgetService.getBudgetById(budgetId);
		if (budget == null) {
			budget = new Budget();
			budget.setBudgetID("No budget found");
			return new ResponseEntity<>(budget, HttpStatusCode.valueOf(200));
		}
		else {
			return new ResponseEntity<>(budget, HttpStatusCode.valueOf(200));
		}
	}
	
	@PostMapping
	public ResponseEntity<BudgetDto> createBudget(@RequestBody BudgetDto budget) {
		log.debug("create controller : " + budget);
		BudgetDto savedBudget = this.budgetService.createBudget(budget);
		return new ResponseEntity<>(savedBudget, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping
	public ResponseEntity<BudgetDto> updateBudget(@RequestBody BudgetDto budget) {
		log.debug("update controller : " + budget);
		BudgetDto updatedBudget = this.budgetService.updateBudget(budget);
		return new ResponseEntity<>(updatedBudget, HttpStatusCode.valueOf(200));
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseTemplate> deleteBudget(@RequestParam("budgetId") String budgetId) {
		log.debug("delete controller : " + budgetId);
		this.budgetService.deleteBudget(budgetId);
		ResponseTemplate responseTemplate = new ResponseTemplate("Budget deleted successfully!");
		return new ResponseEntity<>(responseTemplate, HttpStatusCode.valueOf(200));
	}
}
