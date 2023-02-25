package com.shoppingcart.payload;

import com.shoppingcart.model.Carts;
import com.shoppingcart.model.Product;

import lombok.Data;

@Data
public class LineItemDto {

	private Long id;
//	private Carts cart;
//	private Product product;
	private int quantity;
}
