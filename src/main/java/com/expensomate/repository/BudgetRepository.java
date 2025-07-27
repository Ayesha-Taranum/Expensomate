package com.expensomate.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensomate.entity.Budget;

/**
 * 
 */
@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {

    Optional<Budget> findByBudgetMonth(String budgetMonth);
}
