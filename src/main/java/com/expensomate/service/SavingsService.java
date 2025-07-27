package com.expensomate.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.expensomate.dto.SavingsDto;
import com.expensomate.entity.Savings;

/**
 * 
 */
@Service
public interface SavingsService {
	public List<Savings> getAllSavings();
	public Savings getSavingsById(String id);
	public SavingsDto createSavings(SavingsDto savingsDto);
	public SavingsDto updateSavings(SavingsDto savingsDto);
	public void deleteSavings(String savingId);
}
