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
@Table(name="SAVINGS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Savings {
	@Id
	@Column(name="SAVINGID")
	private String savingID;

	@Column(name="SAVING_AMOUNT")
	private BigDecimal savingAmount;

	@Column(name="SAVING_MONTH")
	private String savingMonth;
}
