package com.expensomate.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensomate.dto.BudgetDto;
import com.expensomate.entity.Budget;
import com.expensomate.mapper.BudgetMapper;
import com.expensomate.repository.BudgetRepository;
import com.expensomate.service.BudgetService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class BudgetServiceImpl implements BudgetService {

	private BudgetRepository budgetRepository;
	
	@Override
	public List<Budget> getAllBudgets() {
		return this.budgetRepository.findAll();
	}

	@Override
	public Budget getBudgetById(String id) {
		Optional<Budget> optionalBudget = this.budgetRepository.findById(id);
		Budget budget = null;
		if(optionalBudget.isPresent()) {
			budget = optionalBudget.get();
		}
		return budget;
	}

	@Override
	public BudgetDto createBudget(BudgetDto budgetDto) {
		log.debug("create service : " + budgetDto);
		Budget budget = BudgetMapper.toBudgetEntity(budgetDto);
		Budget savedBudget = this.budgetRepository.save(budget);
		return BudgetMapper.toBudgetDto(savedBudget);
	}

	@Override
	public BudgetDto updateBudget(BudgetDto budgetDto) {
		log.debug("update service : " + budgetDto);
		Budget budget = BudgetMapper.toBudgetEntity(budgetDto);
		Budget updatedBudget = this.budgetRepository.save(budget);
		return BudgetMapper.toBudgetDto(updatedBudget);
	}

	@Override
	public void deleteBudget(String budgetId) {
		log.debug("delete service : " + budgetId);
		this.budgetRepository.deleteById(budgetId);
	}
}
