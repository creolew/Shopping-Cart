package com.shoppingcart.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.model.User;
import com.shoppingcart.payload.UserDto;

public interface UserService {

	List<UserDto> getAllUsers();
	
	UserDto getUserById(long id);
	
	UserDto updateUser(long userId, UserDto userDto);
	
	void deleteUser(long userId);
	
}
