package com.expensomate.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Table(name="EXPENSES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenses {
	@Id
	@Column(name="EXPENSE_ID")
	private String expenseId;

	@Column(name="CATEGORYID")
	private String categoryID;

	@Column(name="AMOUNT_SPENT")
	private BigDecimal amountSpent;

	@Column(name="BALANCE")
	private BigDecimal balance;

	@Column(name="TRANSACTION_MC")
	private String transactionMc;

	@Column(name="TRANSACTION_DATE")
	private LocalDate transactionDate;

	@Column(name="NOTE")
	private String note;
}
