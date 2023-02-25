package com.shoppingcart.payload;

import com.shoppingcart.model.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDto {
	private Long id;
	
	
	private double price;
	
	
	private String name;
	
	private String added_on;
	
	
	private Long categoryId;
	
	
	

}
