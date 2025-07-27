package com.expensomate.mapper;

import com.expensomate.dto.ExpensesDto;
import com.expensomate.entity.Expenses;

/**
 * 
 */
public class ExpensesMapper {
	private ExpensesMapper() {}

	public static Expenses toExpensesEntity(ExpensesDto expensesDto) {
		return new Expenses(
				expensesDto.getExpenseId(),
				expensesDto.getCategoryID(),
				expensesDto.getAmountSpent(),
				expensesDto.getBalance(),
				expensesDto.getTransactionMc(),
				expensesDto.getTransactionDate(),
				expensesDto.getNote()
		);
	}
	
	public static ExpensesDto toExpensesDto(Expenses expenses) {
		return new ExpensesDto(
				expenses.getExpenseId(),
				expenses.getCategoryID(),
				expenses.getAmountSpent(),
				expenses.getBalance(),
				expenses.getTransactionMc(),
				expenses.getTransactionDate(),
				expenses.getNote()
		);
	}
}
