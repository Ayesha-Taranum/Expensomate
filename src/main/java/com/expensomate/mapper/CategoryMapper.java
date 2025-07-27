package com.expensomate.mapper;

import com.expensomate.dto.CategoryDto;
import com.expensomate.entity.Category;

/**
 * 
 */
public class CategoryMapper {

	private CategoryMapper(){}
	public static Category toCategoryEntity(CategoryDto categoryDto) {
		return new Category(
				categoryDto.getCategoryID(),
				categoryDto.getCategoryName()
		);
	}
	
	public static CategoryDto toCategoryDto(Category category) {
		return new CategoryDto(
				category.getCategoryID(),
				category.getCategoryName()
		);
	}
}
