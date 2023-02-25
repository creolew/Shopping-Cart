package com.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.payload.CartDto;
import com.shoppingcart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping("/createCart")
	public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto){
	
		
		return new ResponseEntity<CartDto>(cartService.createCart(cartDto), HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}/deleteCart")
	public ResponseEntity<String> deleteCart(@PathVariable(name = "id") long id){
		
		if(cartService.deleteCart(id)) {
			return new ResponseEntity("Cart was deleted successfully", HttpStatus.OK);
		}
		
		return new ResponseEntity("Cart was not found", HttpStatus.BAD_REQUEST);
		
		
	} 
	
	
	@GetMapping("/{userId}/getCartByUserId")
	public List<CartDto> getCartByUserId(@PathVariable(name = "userId") long userId){
		
		List<CartDto> listCartDto = cartService.getCartByUserId(userId);
		
		return listCartDto;
	}
	
	
	
}
