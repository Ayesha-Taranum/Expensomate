package com.expensomate.mapper;

import com.expensomate.dto.BudgetDto;
import com.expensomate.entity.Budget;

/**
 * 
 */
public class BudgetMapper {
	private BudgetMapper() {}
	public static Budget toBudgetEntity(BudgetDto budgetDto) {
		return new Budget(
				budgetDto.getBudgetID(),
				budgetDto.getBudgetMonth(),
				budgetDto.getBudgetAmount()
		);
	}
	
	public static BudgetDto toBudgetDto(Budget budget) {
		return new BudgetDto(
				budget.getBudgetID(),
				budget.getBudgetMonth(),
				budget.getBudgetAmount()
		);
	}
}
