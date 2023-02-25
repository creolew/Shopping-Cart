package com.shoppingcart.payload;

import com.shoppingcart.model.User;

import lombok.Data;

@Data
public class CartDto {
	
	private Long id;
	
	private Long userId;
	
}
