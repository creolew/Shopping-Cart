package com.shoppingcart.payload;

import java.util.Set;

import com.shoppingcart.model.Product;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
	
	private Long id;
	
	@NotEmpty
	private String name;
	

}
