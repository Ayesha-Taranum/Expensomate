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

import com.expensomate.dto.CategoryDto;
import com.expensomate.entity.Category;
import com.expensomate.service.impl.CategoryServiceImpl;
import com.expensomate.template.ResponseTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RequestMapping("categories")
@RestController
@AllArgsConstructor
@Slf4j
public class CategoryController {
	
	private CategoryServiceImpl categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> list = this.categoryService.getAllCategories();
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/category")
	public ResponseEntity<Category> getCategory(@RequestParam("categoryId") String categoryId) {
		Category category = this.categoryService.getCategoryById(categoryId);
		if (category == null) {
			category = new Category();
			category.setCategoryID("No category found");
			return new ResponseEntity<>(category, HttpStatusCode.valueOf(200));
		}
		else {
			return new ResponseEntity<>(category, HttpStatusCode.valueOf(200));
		}
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
		log.debug("create controller : " + category);
		CategoryDto savedCategory = this.categoryService.createCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto category) {
		log.debug("update controller : " + category);
		CategoryDto updatedCategory = this.categoryService.updateCategory(category);
		return new ResponseEntity<>(updatedCategory, HttpStatusCode.valueOf(200));
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseTemplate> deleteCategory(@RequestParam("categoryId") String categoryId) {
		log.debug("delete controller : " + categoryId);
		this.categoryService.deleteCategory(categoryId);
		ResponseTemplate responseTemplate = new ResponseTemplate("Category deleted successfully!");
		return new ResponseEntity<>(responseTemplate, HttpStatusCode.valueOf(200));
	}
}
