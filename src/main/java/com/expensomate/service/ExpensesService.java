package com.expensomate.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.expensomate.dto.ExpensesDto;
import com.expensomate.entity.Expenses;

/**
 * 
 */
@Service
public interface ExpensesService {
	public List<Expenses> getAllExpenses();
	public Expenses getExpensesById(String id);
	public ExpensesDto createExpenses(ExpensesDto expensesDto, String userId);
	public ExpensesDto updateExpenses(ExpensesDto expensesDto);
	public void deleteExpenses(String expenseId);
}
