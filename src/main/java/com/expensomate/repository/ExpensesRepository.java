package com.expensomate.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expensomate.entity.Expenses;

/**
 * 
 */
@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, String> {
    @Query(
        value = "SELECT SUM(amount_spent) FROM EXPENSES WHERE EXTRACT(YEAR FROM transaction_date) = :year AND EXTRACT(MONTH FROM transaction_date) = :month",
        nativeQuery = true
    )
    BigDecimal findTotalExpensesByMonth(@Param("year") int year, @Param("month") int month);
}
