package com.shoppingcart.payload;

import java.util.List;

import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Product;

import lombok.Data;

@Data
public class CheckoutDto {

	private Long id;
	private double totalPrice;
	
	

}
