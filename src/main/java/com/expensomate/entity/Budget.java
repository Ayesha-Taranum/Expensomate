package com.expensomate.entity;

import java.math.BigDecimal;

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
@Table(name="BUDGET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
	@Id
	@Column(name="BUDGETID")
	private String budgetID;

	@Column(name="BUDGET_MONTH")
	private String budgetMonth;

	@Column(name="BUDGET_AMOUNT")
	private BigDecimal budgetAmount;
}
