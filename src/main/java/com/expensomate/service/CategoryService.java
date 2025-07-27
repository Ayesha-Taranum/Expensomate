package com.expensomate.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.expensomate.dto.CategoryDto;
import com.expensomate.entity.Category;

/**
 * 
 */
@Service
public interface CategoryService {
	public List<Category> getAllCategories();
	public Category getCategoryById(String id);
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto);
	public void deleteCategory(String categoryId);
}
