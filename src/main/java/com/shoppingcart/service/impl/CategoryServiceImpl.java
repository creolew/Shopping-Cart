package com.shoppingcart.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Category;
import com.shoppingcart.payload.CategoryDto;
import com.shoppingcart.repository.CategoryRepository;
import com.shoppingcart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	
	
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}



	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		//convert DTO to entity
		Category category = mapToEntity(categoryDto);

		Category newCategory = categoryRepository.save(category);
		
		
		
		//convert entity to Dto
		CategoryDto categoryResponse = mapToDto(newCategory);

		return categoryResponse;
	}



	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> listCategories = categoryRepository.findAll();
		
		List<CategoryDto> listCategoriesDto = listCategories.stream()
													        .map(category -> mapToDto(category))
													        .collect(Collectors.toList());
		
		
		return listCategoriesDto;
	}
	


		@Override
		public CategoryDto getCategoryById(long id) {
			
			Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
			
			return mapToDto(category);
		}



		@Override
		public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
			
			Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

			category.setName(categoryDto.getName());
			
			Category updatedCategory = categoryRepository.save(category);
			
			return mapToDto(updatedCategory);
		}

		
		
		@Override
		public void deleteCategoryById(long id) {
			Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

			categoryRepository.delete(category);
		}
		
		
		//convert entity to Dto
		private CategoryDto mapToDto(Category category) {
			CategoryDto categoryDto = new CategoryDto();
			
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());
			
			return categoryDto;
		}
		
		//convert Dto to entity
			private Category mapToEntity(CategoryDto categoryDto) {
				Category category = new Category();
				
				category.setId(categoryDto.getId());
				
				category.setName(categoryDto.getName());
				
				return category;
			}



			


	
	
	
}
