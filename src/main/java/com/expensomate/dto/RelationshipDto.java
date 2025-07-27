package com.expensomate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipDto {
    private String relationshipId;
	private String userID;
	private String expenseID;
	private String budgetID;
	private String savingID;
}
