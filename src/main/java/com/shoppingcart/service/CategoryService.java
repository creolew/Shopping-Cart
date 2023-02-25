package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.payload.CategoryDto;


public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto getCategoryById (long id);
	
	CategoryDto updateCategory(CategoryDto categoryDto, long id);
	
	void deleteCategoryById(long id);
	
}
