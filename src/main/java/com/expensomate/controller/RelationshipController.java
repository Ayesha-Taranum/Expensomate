package com.expensomate.controller;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensomate.dto.RelationshipDto;
import com.expensomate.entity.Relationship;
import com.expensomate.service.impl.RelationshipServiceImpl;
import com.expensomate.template.ResponseTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RequestMapping("relationships")
@RestController
@AllArgsConstructor
@Slf4j
public class RelationshipController {
	
	private RelationshipServiceImpl relationshipService;
	
	@GetMapping
	public ResponseEntity<List<Relationship>> getRelationships() {
		List<Relationship> list = this.relationshipService.getAllRelationships();
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/relationship")
	public ResponseEntity<Relationship> getRelationship(@RequestParam("relationshipId") String relationshipId) {
		Relationship relationship = this.relationshipService.getRelationshipById(relationshipId);
		if (relationship == null) {
			relationship = new Relationship();
			relationship.setRelationshipId("No relationship found");
			return new ResponseEntity<>(relationship, HttpStatusCode.valueOf(200));
		}
		else {
			return new ResponseEntity<>(relationship, HttpStatusCode.valueOf(200));
		}
	}
	
	@PostMapping
	public ResponseEntity<RelationshipDto> createRelationship(@RequestBody RelationshipDto relationship) {
		log.debug("create controller : " + relationship);
		RelationshipDto savedRelationship = this.relationshipService.createRelationship(relationship);
		return new ResponseEntity<>(savedRelationship, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping
	public ResponseEntity<RelationshipDto> updateRelationship(@RequestBody RelationshipDto relationship) {
		log.debug("update controller : " + relationship);
		RelationshipDto updatedRelationship = this.relationshipService.updateRelationship(relationship);
		return new ResponseEntity<>(updatedRelationship, HttpStatusCode.valueOf(200));
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseTemplate> deleteRelationship(@RequestParam("relationshipId") String relationshipId) {
		log.debug("delete controller : " + relationshipId);
		this.relationshipService.deleteRelationship(relationshipId);
		ResponseTemplate responseTemplate = new ResponseTemplate("Relationship deleted successfully!");
		return new ResponseEntity<>(responseTemplate, HttpStatusCode.valueOf(200));
	}
}
