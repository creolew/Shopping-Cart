package com.shoppingcart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.payload.UserDto;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository 	userRepository;
	
	
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserDto> listUsers = userRepository.findAll().stream().map(user -> mapToDto(user)).collect(Collectors.toList());
		return listUsers;
	}



	@Override
	public UserDto getUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(
		  		() -> new ResourceNotFoundException("User", "id", id)
			  	  );
		return mapToDto(user);
	}
	
	@Override
	public UserDto updateUser(long userId, UserDto userDto) {

		User user = userRepository.findById(userId).orElseThrow(
		  		() -> new ResourceNotFoundException("User", "id", userId)
			  	  );
		
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		
		userRepository.save(user);
		

		return mapToDto(user);
	}
	
	@Override
	public void deleteUser(long userId) {
		User user = userRepository.findById(userId).orElseThrow(
		  		() -> new ResourceNotFoundException("User", "id", userId)
			  	  );
		
		userRepository.delete(user);
		
	}

	
	//convert entity to Dto
	private UserDto mapToDto(User user) {

		UserDto userDto = new UserDto();

		userDto.setId(user.getId());

		userDto.setName(user.getName());

		userDto.setUsername(user.getUsername());

		userDto.setEmail(user.getEmail());



		return userDto;
	}
				
	//convert Dto to entity
	private User mapToEntity(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getId());

		user.setName(userDto.getName());

		user.setUsername(userDto.getUsername());

		user.setEmail(userDto.getEmail());



		return user;
	}
	
}
