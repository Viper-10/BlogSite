package com.rest.webservices.demo.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.demo.Entity.User;
import com.rest.webservices.demo.Repositories.UserDao;
import com.rest.webservices.demo.exceptions.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao; 
	
	@GetMapping(path = "/users")
	public List<User> handleGetAllUsers(){
		return userDao.getAllUsers();
	}
	
	@GetMapping(path = "/users/{id}")
	public User handleGetSingleUser(@PathVariable int id) throws UserNotFoundException{
		
		 User user = userDao.getUserById(id);
		 if(user == null) {
			 throw new UserNotFoundException("User with id " + id + " doesn't exist");
		 }
		 
		 return user; 
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> handlePostSingleUser(@Valid @RequestBody User user) {
		User savedUser = userDao.addUser(user); 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); 
		
		return ResponseEntity.created(location).build(); 
	}
	
	
	@DeleteMapping (path = "/users/{id}")
	public void deleteUserById(@PathVariable int id) throws UserNotFoundException {
		boolean userExist = userDao.deleteUserById(id);
		
		if(!userExist) {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}
	}
	
	
	
	
	
	
	
//	@GetMapping(path = "/users/{id}")
//	public User handleGetUser(@PathVariable int id) throws UserNotFoundException{
//		User user = userDao.getUserById(id);
//		
//		if(user == null) {
//			throw new UserNotFoundException("id : " + id + " Not found");
//		}
//				
//		return user; 
//		 
//	}
//	
//	@PostMapping(path = "/users")
//	public ResponseEntity<Object> handlePostUser(@Valid @RequestBody User user) {
//		User savedUser = userDao.addUser(user);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); 
//		
//		return ResponseEntity.created(location).build(); 
//	}
//	
//	@DeleteMapping("/users/{id}")
//	public void deleteUserById(@PathVariable int id) throws UserNotFoundException{
//		boolean found = userDao.removeUserById(id); 
//		
//		if(!found) {
//			throw new UserNotFoundException("Id : " + id + " is not created");
//		}		
//	}
	
}
