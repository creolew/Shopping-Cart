package com.shoppingcart.service;

import com.shoppingcart.payload.LoginDto;
import com.shoppingcart.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	

	String register(RegisterDto registerDto);
	
}
