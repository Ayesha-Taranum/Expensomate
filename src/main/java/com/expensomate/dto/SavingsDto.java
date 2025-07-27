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
public class SavingsDto {
	private String savingID;
	private BigDecimal savingAmount;
	private String savingMonth;
}
