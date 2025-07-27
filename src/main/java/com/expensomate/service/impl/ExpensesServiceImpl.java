package com.expensomate.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.expensomate.dto.ExpensesDto;
import com.expensomate.entity.*;
import com.expensomate.mapper.ExpensesMapper;
import com.expensomate.repository.BudgetRepository;
import com.expensomate.repository.ExpensesRepository;
import com.expensomate.repository.RelationshipRepository;
import com.expensomate.repository.SavingsRepository;
import com.expensomate.service.ExpensesService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {

	private ExpensesRepository expensesRepository;
	private BudgetRepository budgetRepository;
	private SavingsRepository savingsRepository; 
	private RelationshipRepository relationshipRepository; 
	
    public List<Expenses> getAllExpenses() {
		return this.expensesRepository.findAll();
	}                                     
	
	public Expenses getExpensesById(String id) {
		Optional<Expenses> optionalEmployee = this.expensesRepository.findById(id);
		Expenses employee = null;
		if(optionalEmployee.isPresent()) {
			employee = optionalEmployee.get();
		}
		return employee;
	}

	@Override
	@Transactional 
	public ExpensesDto createExpenses(ExpensesDto expensesDto, String userId) {
		log.debug("create service for expense: {}", expensesDto);
        
        String budgetMonth = expensesDto.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));

        Budget monthlyBudget = budgetRepository.findByBudgetMonth(budgetMonth)
                .orElseThrow(() -> new IllegalStateException("Cannot log expense. No budget found for month: " + budgetMonth));

        BigDecimal balance = monthlyBudget.getBudgetAmount().subtract(expensesDto.getAmountSpent());
        expensesDto.setBalance(balance);

		Expenses expenses = ExpensesMapper.toExpensesEntity(expensesDto);
		Expenses savedExpense = this.expensesRepository.save(expenses);
		log.debug("Saved new expense with ID: {}", savedExpense.getExpenseId());

        Savings monthlySavings = savingsRepository.findBySavingMonth(budgetMonth)
                .orElseThrow(() -> new IllegalStateException("Cannot create relationship. No savings record found for month: " + budgetMonth));

        Relationship newRelationship = new Relationship(
            UUID.randomUUID().toString(), // Generate a new ID for the relationship
            userId,
            savedExpense.getExpenseId(),
            monthlyBudget.getBudgetID(),
            monthlySavings.getSavingID()
        );
        relationshipRepository.save(newRelationship);
        log.debug("Created new relationship record: {}", newRelationship.getRelationshipId());
		
		return ExpensesMapper.toExpensesDto(savedExpense);
	}

	@Override
	public ExpensesDto updateExpenses(ExpensesDto expensesDto) {
		log.debug("update service for expense: " + expensesDto);

        BigDecimal balance = calculateBalanceForExpense(expensesDto.getTransactionDate(), expensesDto.getAmountSpent());
        expensesDto.setBalance(balance);
		
		Expenses expenses = ExpensesMapper.toExpensesEntity(expensesDto);
		Expenses updatedExpenses = this.expensesRepository.save(expenses);
		return ExpensesMapper.toExpensesDto(updatedExpenses);
	}

	public void deleteExpenses(String expenseId) {
		log.debug("delete service : "+expenseId);
		this.expensesRepository.deleteById(expenseId);
	}
    
    private BigDecimal calculateBalanceForExpense(LocalDate transactionDate, BigDecimal amountSpent) {
        if (transactionDate == null || amountSpent == null) return BigDecimal.ZERO;
        String budgetMonth = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        Optional<Budget> budgetOpt = budgetRepository.findByBudgetMonth(budgetMonth);
        BigDecimal budgetAmount = budgetOpt.map(Budget::getBudgetAmount).orElse(BigDecimal.ZERO);
        return budgetAmount.subtract(amountSpent);
    }
}
