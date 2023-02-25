package com.shoppingcart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Carts;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import com.shoppingcart.payload.CartDto;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	CartRepository cartRepository;
	
	UserRepository userRepository;
	
	
	
	public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}



	@Override
	public CartDto createCart(CartDto cartDto) {
		
		Carts cart = mapToEntity(cartDto);
		User user = getUserLoggingIn();
		cart.setUser(user);
		Carts newCart = cartRepository.save(cart);
		
		CartDto cartResponse = mapToDto(newCart);

		return cartResponse;
	}
	
	@Override
	public boolean deleteCart(Long cartId) {
		Carts cart = cartRepository.findById(cartId).get();
		
		User user = getUserLoggingIn();
		
		if(cart.getUser().getId() == user.getId()) {
			cartRepository.delete(cart);
			return true;
		}
		return false;
		
		
	}
	

		@Override
		public List<CartDto> getCartByUserId(Long userId) {

			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

			List<Carts> carts = cartRepository.findCartsByUserId(userId);
			List<CartDto> list = carts.stream().map(cart -> mapToDto(cart)).collect(Collectors.toList());
			return list;
		}


	
	
	
	//convert entity to Dto
			private CartDto mapToDto(Carts cart) {
				
				CartDto cartDto = new CartDto();
				
				cartDto.setId(cart.getId());
				cartDto.setUserId(cart.getUser().getId());
				
				return cartDto;
			}
				
		//convert Dto to entity
			private Carts mapToEntity(CartDto cartDto) {
				
				
				Carts cart = new Carts();
				
				cart.setId(cartDto.getId() );
				

				return cart;
			}

			
			private User getUserLoggingIn() {
				UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
		                .getPrincipal();
				String userEmail = userDetails.getUsername();
				User user = userRepository.findByEmail(userEmail).get();
				return user;
			}




			

}
