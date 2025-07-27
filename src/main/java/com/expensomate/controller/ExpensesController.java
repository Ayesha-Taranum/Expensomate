package com.expensomate.controller;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensomate.dto.ExpensesDto;
import com.expensomate.entity.Expenses;
import com.expensomate.service.impl.ExpensesServiceImpl;
import com.expensomate.template.ResponseTemplate; // Note: You will need to create this class

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RequestMapping("expenses")
@RestController
@AllArgsConstructor
@Slf4j
public class ExpensesController {
	
	private ExpensesServiceImpl expensesService;
	
	@GetMapping
	public ResponseEntity<List<Expenses>> getExpenses() {
		List<Expenses> list = this.expensesService.getAllExpenses();
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/expense")
	public ResponseEntity<Expenses> getExpense(@RequestParam("expenseId") String expenseId) {
		Expenses expense = this.expensesService.getExpensesById(expenseId);
		if (expense == null) {
			expense = new Expenses();
			expense.setExpenseId("No expense found");
			return new ResponseEntity<>(expense, HttpStatusCode.valueOf(200));
		}
		else {
			return new ResponseEntity<>(expense, HttpStatusCode.valueOf(200));
		}
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<ExpensesDto> createExpense(
            @PathVariable("userId") String userId, 
            @RequestBody ExpensesDto expense) {
		log.debug("create controller for user {}: {}", userId, expense);
		ExpensesDto savedExpense = this.expensesService.createExpenses(expense, userId);
		return new ResponseEntity<>(savedExpense, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping
	public ResponseEntity<ExpensesDto> updateExpense(@RequestBody ExpensesDto expense) {
		log.debug("update controller : " + expense);
		ExpensesDto updatedExpense = this.expensesService.updateExpenses(expense);
		return new ResponseEntity<>(updatedExpense, HttpStatusCode.valueOf(200));
	}
	@DeleteMapping
	public ResponseEntity<ResponseTemplate> deleteExpense(@RequestParam("expenseId") String expenseId) {
		log.debug("delete controller : " + expenseId);
		this.expensesService.deleteExpenses(expenseId);
		ResponseTemplate responseTemplate = new ResponseTemplate("Expense deleted successfully!");
		return new ResponseEntity<>(responseTemplate, HttpStatusCode.valueOf(200));
	}
}
