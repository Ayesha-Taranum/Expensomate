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

import com.expensomate.dto.SavingsDto;
import com.expensomate.entity.Savings;
import com.expensomate.service.impl.SavingsServiceImpl;
import com.expensomate.template.ResponseTemplate; // Note: You will need to create this class

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RequestMapping("savings")
@RestController
@AllArgsConstructor
@Slf4j
public class SavingsController {
	
	private SavingsServiceImpl savingsService;
	
	@GetMapping
	public ResponseEntity<List<Savings>> getSavings() {
		List<Savings> list = this.savingsService.getAllSavings();
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/saving")
	public ResponseEntity<Savings> getSaving(@RequestParam("savingId") String savingId) {
		Savings saving = this.savingsService.getSavingsById(savingId);
		if (saving == null) {
			saving = new Savings();
			saving.setSavingID("No saving found");
			return new ResponseEntity<>(saving, HttpStatusCode.valueOf(200));
		}
		else {
			return new ResponseEntity<>(saving, HttpStatusCode.valueOf(200));
		}
	}
	
	@PostMapping
	public ResponseEntity<SavingsDto> createSaving(@RequestBody SavingsDto saving) {
		log.debug("create controller : " + saving);
		SavingsDto savedSaving = this.savingsService.createSavings(saving);
		return new ResponseEntity<>(savedSaving, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping
	public ResponseEntity<SavingsDto> updateSaving(@RequestBody SavingsDto saving) {
		log.debug("update controller : " + saving);
		SavingsDto updatedSaving = this.savingsService.updateSavings(saving);
		return new ResponseEntity<>(updatedSaving, HttpStatusCode.valueOf(200));
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseTemplate> deleteSaving(@RequestParam("savingId") String savingId) {
		log.debug("delete controller : " + savingId);
		this.savingsService.deleteSavings(savingId);
		ResponseTemplate responseTemplate = new ResponseTemplate("Saving deleted successfully!");
		return new ResponseEntity<>(responseTemplate, HttpStatusCode.valueOf(200));
	}
}
