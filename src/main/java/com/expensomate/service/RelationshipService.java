package com.expensomate.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.expensomate.dto.RelationshipDto;
import com.expensomate.entity.Relationship;

/**
 * 
 */
@Service
public interface RelationshipService {
	public List<Relationship> getAllRelationships();
	public Relationship getRelationshipById(String id);
	public RelationshipDto createRelationship(RelationshipDto relationshipDto);
	public RelationshipDto updateRelationship(RelationshipDto relationshipDto);
	public void deleteRelationship(String relationshipId);
}
