package com.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.model.User;
import com.shoppingcart.payload.UserDto;
import com.shoppingcart.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/getAll")
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}/getUserById")
	public ResponseEntity<UserDto> getUserByid(@PathVariable(name = "userId") long userId) {
		
		UserDto user =userService.getUserById(userId);
		
		return ResponseEntity.ok(user);
		
		
	}
	
	@PutMapping("/{userId}/updateUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDto> updateUser(@PathVariable(name = "userId") long userId, 
											  @RequestBody UserDto userDto){
		
		
		return new ResponseEntity<UserDto>(userService.updateUser(userId, userDto)
		,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}/deleteUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") long userId){
		userService.deleteUser(userId);
		return new ResponseEntity("User with id: " + userId + " was deleted successfully", HttpStatus.OK);
	}
	
}
