package com.expensomate.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.expensomate.dto.RelationshipDto;
import com.expensomate.entity.Relationship;
import com.expensomate.mapper.RelationshipMapper;
import com.expensomate.repository.RelationshipRepository;
import com.expensomate.service.RelationshipService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class RelationshipServiceImpl implements RelationshipService {

	private RelationshipRepository relationshipRepository;
	
	@Override
	public List<Relationship> getAllRelationships() {
		return this.relationshipRepository.findAll();
	}

	@Override
	public Relationship getRelationshipById(String id) {
		Optional<Relationship> optionalRelationship = this.relationshipRepository.findById(id);
		Relationship relationship = null;
		if(optionalRelationship.isPresent()) {
			relationship = optionalRelationship.get();
		}
		return relationship;
	}

	@Override
	public RelationshipDto createRelationship(RelationshipDto relationshipDto) {
		log.debug("create service : " + relationshipDto);
		Relationship relationship = RelationshipMapper.toRelationshipEntity(relationshipDto);
		Relationship savedRelationship = this.relationshipRepository.save(relationship);
		return RelationshipMapper.toRelationshipDto(savedRelationship);
	}

	@Override
	public RelationshipDto updateRelationship(RelationshipDto relationshipDto) {
		log.debug("update service : " + relationshipDto);
		Relationship relationship = RelationshipMapper.toRelationshipEntity(relationshipDto);
		Relationship updatedRelationship = this.relationshipRepository.save(relationship);
		return RelationshipMapper.toRelationshipDto(updatedRelationship);
	}

	@Override
	public void deleteRelationship(String relationshipId) {
		log.debug("delete service : " + relationshipId);
		this.relationshipRepository.deleteById(relationshipId);
	}
}
