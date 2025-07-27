package com.expensomate.mapper;

import com.expensomate.dto.RelationshipDto;
import com.expensomate.entity.Relationship;

/**
 * 
 */
public class RelationshipMapper {
	private RelationshipMapper() {}
	public static Relationship toRelationshipEntity(RelationshipDto relationshipDto) {
		return new Relationship(
                relationshipDto.getRelationshipId(),
				relationshipDto.getUserID(),
				relationshipDto.getExpenseID(),
				relationshipDto.getBudgetID(),
				relationshipDto.getSavingID()
		);
	}
	
	public static RelationshipDto toRelationshipDto(Relationship relationship) {
		return new RelationshipDto(
                relationship.getRelationshipId(),
				relationship.getUserID(),
				relationship.getExpenseID(),
				relationship.getBudgetID(),
				relationship.getSavingID()
		);
	}
}
