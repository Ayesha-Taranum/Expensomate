package com.expensomate.service.impl;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensomate.dto.SavingsDto;
import com.expensomate.entity.Budget;
import com.expensomate.entity.Savings;
import com.expensomate.mapper.SavingsMapper;
import com.expensomate.repository.BudgetRepository;
import com.expensomate.repository.ExpensesRepository;
import com.expensomate.repository.SavingsRepository;
import com.expensomate.service.SavingsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class SavingsServiceImpl implements SavingsService {

	private SavingsRepository savingsRepository;
	private ExpensesRepository expensesRepository;
	private BudgetRepository budgetRepository;
	
	@Override
	public List<Savings> getAllSavings() {
		return this.savingsRepository.findAll();
	}

	@Override
	public Savings getSavingsById(String id) {
		Optional<Savings> optionalSavings = this.savingsRepository.findById(id);
		Savings saving = null;
		if(optionalSavings.isPresent()) {
			saving = optionalSavings.get();
		}
		return saving;
	}

	@Override
	public SavingsDto createSavings(SavingsDto savingsDto) {
		log.debug("create service for savings with month : " + savingsDto.getSavingMonth());
		
		BigDecimal calculatedAmount = calculateSavingAmountForMonth(savingsDto.getSavingMonth());
		savingsDto.setSavingAmount(calculatedAmount);
		
		Savings savings = SavingsMapper.toSavingsEntity(savingsDto);
		Savings savedSavings = this.savingsRepository.save(savings);
		return SavingsMapper.toSavingsDto(savedSavings);
	}

	@Override
	public SavingsDto updateSavings(SavingsDto savingsDto) {
		log.debug("update service for savings with month : " + savingsDto.getSavingMonth());

		BigDecimal calculatedAmount = calculateSavingAmountForMonth(savingsDto.getSavingMonth());
		savingsDto.setSavingAmount(calculatedAmount);

		Savings savings = SavingsMapper.toSavingsEntity(savingsDto);
		Savings updatedSavings = this.savingsRepository.save(savings);
		return SavingsMapper.toSavingsDto(updatedSavings);
	}

	@Override
	public void deleteSavings(String savingId) {
		log.debug("delete service : " + savingId);
		this.savingsRepository.deleteById(savingId);
	}


	private BigDecimal calculateSavingAmountForMonth(String savingMonth) {
		Optional<Budget> budgetOpt = budgetRepository.findByBudgetMonth(savingMonth);
		if (budgetOpt.isEmpty()) {
			log.warn("No budget found for month: {}. Defaulting to zero.", savingMonth);
			return BigDecimal.ZERO; 
		}
		BigDecimal budgetAmount = budgetOpt.get().getBudgetAmount();

		YearMonth yearMonth;
		try {
			yearMonth = YearMonth.parse(savingMonth); // Assumes "YYYY-MM" format
		} catch (DateTimeParseException e) {
			log.error("Invalid month format: {}. Expected YYYY-MM.", savingMonth);
			return BigDecimal.ZERO;
		}

		BigDecimal totalExpenses = expensesRepository.findTotalExpensesByMonth(yearMonth.getYear(), yearMonth.getMonthValue());
		if (totalExpenses == null) {
			totalExpenses = BigDecimal.ZERO; 
		}

		log.debug("Calculation for {}: Budget = {}, Expenses = {}", savingMonth, budgetAmount, totalExpenses);
		
		return budgetAmount.subtract(totalExpenses);
	}
}
