package com.expensomate.entity;

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
@Table(name="RELATIONSHIP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relationship {
	@Id
	@Column(name="RELATIONSHIP_ID")
    private String relationshipId; 

	@Column(name="USERID")
	private String userID;

	@Column(name="EXPENSEID")
	private String expenseID;

	@Column(name="BUDGETID")
	private String budgetID;

	@Column(name="SAVINGID")
	private String savingID;
}
