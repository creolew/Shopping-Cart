package com.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.payload.CategoryDto;
import com.shoppingcart.payload.CheckoutDto;
import com.shoppingcart.service.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	CheckoutService checkoutService;

	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/{userId}/createCheckout")
	public ResponseEntity<CheckoutDto> createCheckout(@PathVariable(name = "userId") Long userId){
		
		
		return new ResponseEntity<CheckoutDto>(checkoutService.createCheckout(userId), HttpStatus.CREATED);

	}
	
	@GetMapping("/{checkoutId}/getCheckoutById")
	public CheckoutDto getCheckoutById(@PathVariable (name = "checkoutId") Long checkoutId) {
		
		return checkoutService.getCheckoutById(checkoutId);
		
	}
	
	@GetMapping("/{userId}/getCheckoutByUserId")
	public CheckoutDto getCheckoutByUserId(@PathVariable (name = "userId") Long userId) {
		
		return checkoutService.getCheckoutByUserId(userId);
		
	}
	
}
