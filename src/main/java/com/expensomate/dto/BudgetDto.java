package com.expensomate.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {
	private String budgetID;
	private String budgetMonth;
	private BigDecimal budgetAmount;
}
