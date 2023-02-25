package com.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.model.Carts;
import com.shoppingcart.model.Checkout;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Product;
import com.shoppingcart.payload.CartDto;
import com.shoppingcart.payload.CheckoutDto;
import com.shoppingcart.payload.LineItemDto;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.repository.CheckoutRepository;
import com.shoppingcart.repository.LineItemRepository;
import com.shoppingcart.repository.ProductRepository;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.service.CartService;
import com.shoppingcart.service.CheckoutService;
import com.shoppingcart.service.LineItemService;
import com.shoppingcart.service.UserService;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	CheckoutRepository checkoutRepository;
	
	CartService cartService;
	
	CartRepository cartRepository;
	
	LineItemRepository lineItemRepository;
	
	ProductRepository productRepository;
	




	public CheckoutServiceImpl(CheckoutRepository checkoutRepository, CartService cartService,
			CartRepository cartRepository, LineItemRepository lineItemRepository, ProductRepository productRepository) {
		this.checkoutRepository = checkoutRepository;
		this.cartService = cartService;
		this.cartRepository = cartRepository;
		this.lineItemRepository = lineItemRepository;
		this.productRepository = productRepository;
	}



	@Override
	public CheckoutDto createCheckout( Long userId) {
		
		//get cart by user id
		List<Carts> listCarts = cartRepository.findCartsByUserId(userId);
		
		
		System.out.println("List cart: " + listCarts);

		//get line item  by cart
		List <LineItem> lineItems = new ArrayList<>();
		
		
		for(int i = 0; i< listCarts.size(); i++) {
			
			Long cartId = listCarts.get(i).getId();
			
			System.out.println(cartId);
			
			List <LineItem> tempLineItem = lineItemRepository.findAllByCartId(cartId);
			
			for(LineItem lineItem : tempLineItem) {
				System.out.println(lineItem);
				lineItems.add(lineItem);
			}
	
			System.out.println("size: "+ lineItemRepository.findAllByCartId(cartId).size());
		
		}
		
		//calculate total price of each user
		double totalPrice = 0; 
		
		for(LineItem lineItem : lineItems) {
			double price = lineItem.getProduct().getPrice();
			int quantity = lineItem.getQuantity();
			
			System.out.println("Price: " + price);
			System.out.println("Quantity: " + quantity);
			
			totalPrice += price*quantity;
		}
		
		System.out.println("Total Price: " + totalPrice);
		
		//create entity checkout
		Checkout checkout = new Checkout();
		checkout.setTotalPrice(totalPrice);
		
		
		
		
		checkoutRepository.save(checkout);
		
		//fill field checkout_id in line item
		
		for(LineItem lineItem : lineItems) {
			lineItem.setCheckout(checkout);
			
			lineItemRepository.save(lineItem);
		}
	
		//convert to checkoutDto
		CheckoutDto checkoutDto = new CheckoutDto();
		
		checkoutDto.setId(checkout.getId());
		
		checkoutDto.setTotalPrice(checkout.getTotalPrice());
		
		
		
		

		return checkoutDto;
	}



	@Override
	public CheckoutDto getCheckoutById(Long checkoutId) {
		Checkout checkout = checkoutRepository.findById(checkoutId).get();
		
		
		//convert to checkoutDto
		CheckoutDto checkoutDto = new CheckoutDto();
				
		checkoutDto.setId(checkout.getId());
				
		checkoutDto.setTotalPrice(checkout.getTotalPrice());
				
		return checkoutDto;
	}



	@Override
	public CheckoutDto getCheckoutByUserId(Long userId) {
		
		//get cart by user id
		List<Carts> listCarts = cartRepository.findCartsByUserId(userId);
		
		
		System.out.println("List cart: " + listCarts);

		//get line item  by cart
		List <LineItem> lineItems = new ArrayList<>();
		
		
		for(int i = 0; i< listCarts.size(); i++) {
			
			Long cartId = listCarts.get(i).getId();
			
			System.out.println(cartId);
			
			List <LineItem> tempLineItem = lineItemRepository.findAllByCartId(cartId);
			
			for(LineItem lineItem : tempLineItem) {
				System.out.println(lineItem);
		
				lineItems.add(lineItem);
			}
	
			System.out.println("size: "+ lineItemRepository.findAllByCartId(cartId).size());
		
		}
		
		//get checkout entity
		Checkout checkout = new Checkout();
		for(LineItem lineItem : lineItems) {
			Long checkoutID = lineItem.getCheckout().getId();
			checkout = checkoutRepository.findById(checkoutID).get();
			break;
			
		}
		
		
		//convert to checkoutDto
		CheckoutDto checkoutDto = new CheckoutDto();
				
		checkoutDto.setId(checkout.getId());
				
		checkoutDto.setTotalPrice(checkout.getTotalPrice());
				
		return checkoutDto;
	
	}
	
	
	
	

	



	//-------------------------------------------------------------------------------
		


















}
