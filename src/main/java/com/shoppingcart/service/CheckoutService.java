package com.shoppingcart.service;

import com.shoppingcart.model.Checkout;
import com.shoppingcart.payload.CheckoutDto;

public interface CheckoutService {

	CheckoutDto createCheckout( Long userId);
	
	CheckoutDto getCheckoutById(Long checkoutId);
	
	CheckoutDto getCheckoutByUserId(Long userId);
	
}
