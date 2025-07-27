package com.expensomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensomate.entity.Relationship;

/**
 * 
 */
@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, String> {

}
