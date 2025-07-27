package com.expensomate.mapper;

import com.expensomate.dto.SavingsDto;
import com.expensomate.entity.Savings;

/**
 * 
 */
public class SavingsMapper {
	private SavingsMapper() {}
	public static Savings toSavingsEntity(SavingsDto savingsDto) {
		return new Savings(
				savingsDto.getSavingID(),
				savingsDto.getSavingAmount(),
				savingsDto.getSavingMonth()
		);
	}
	
	public static SavingsDto toSavingsDto(Savings savings) {
		return new SavingsDto(
				savings.getSavingID(),
				savings.getSavingAmount(),
				savings.getSavingMonth()
		);
	}
}
