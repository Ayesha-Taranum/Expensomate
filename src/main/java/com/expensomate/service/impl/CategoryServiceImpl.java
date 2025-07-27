package com.expensomate.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensomate.dto.CategoryDto;
import com.expensomate.entity.Category;
import com.expensomate.mapper.CategoryMapper;
import com.expensomate.repository.CategoryRepository;
import com.expensomate.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(String id) {
		Optional<Category> optionalCategory = this.categoryRepository.findById(id);
		Category category = null;
		if(optionalCategory.isPresent()) {
			category = optionalCategory.get();
		}
		return category;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		log.debug("create service : " + categoryDto);
		Category category = CategoryMapper.toCategoryEntity(categoryDto);
		Category savedCategory = this.categoryRepository.save(category);
		return CategoryMapper.toCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		log.debug("update service : " + categoryDto);
		Category category = CategoryMapper.toCategoryEntity(categoryDto);
		Category updatedCategory = this.categoryRepository.save(category);
		return CategoryMapper.toCategoryDto(updatedCategory);
	}

	@Override
	public void deleteCategory(String categoryId) {
		log.debug("delete service : " + categoryId);
		this.categoryRepository.deleteById(categoryId);
	}
}
