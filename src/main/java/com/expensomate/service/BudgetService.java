package com.expensomate.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.expensomate.dto.BudgetDto;
import com.expensomate.entity.Budget;

/**
 * 
 */
@Service
public interface BudgetService {
	public List<Budget> getAllBudgets();
	public Budget getBudgetById(String id);
	public BudgetDto createBudget(BudgetDto budgetDto);
	public BudgetDto updateBudget(BudgetDto budgetDto);
	public void deleteBudget(String budgetId);
}
