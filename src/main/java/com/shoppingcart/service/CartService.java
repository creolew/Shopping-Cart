package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.payload.CartDto;

public interface CartService {

	CartDto createCart(CartDto cartDto);
	
	boolean deleteCart(Long cartId);
	
	List<CartDto> getCartByUserId(Long userId);
}
