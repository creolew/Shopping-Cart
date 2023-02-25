package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.payload.CategoryDto;
import com.shoppingcart.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//create category api
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<CategoryDto> getAllCategories (){
		
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/{id}/getById")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id) {
		
		return  ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	
	
	@PutMapping("/{id}/updated")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> updateCategoryById( @RequestBody CategoryDto categoryDto,
														
														   @PathVariable(name = "id") long id){

		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto, id), HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") long id  ){
		
		categoryService.deleteCategoryById(id);
		
		return new ResponseEntity<>("Category with id: " + id + " was deleted successfully", HttpStatus.OK);
	}

	
}
