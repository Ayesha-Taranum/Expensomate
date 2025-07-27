package com.expensomate.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesDto {
	private String expenseId;
	private String categoryID;
	private BigDecimal amountSpent;
	private BigDecimal balance;
	private String transactionMc;
	private LocalDate transactionDate;
	private String note;
}
