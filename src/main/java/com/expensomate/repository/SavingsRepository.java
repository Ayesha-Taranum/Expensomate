package com.expensomate.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensomate.entity.Savings;

/**
 * 
 */
@Repository
public interface SavingsRepository extends JpaRepository<Savings, String> {
    
    Optional<Savings> findBySavingMonth(String savingMonth);
}
