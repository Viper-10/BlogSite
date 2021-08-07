package com.rest.webservices.demo.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.demo.Entity.Post;
import com.rest.webservices.demo.Entity.User;
import com.rest.webservices.demo.Repositories.PostDao;
import com.rest.webservices.demo.Repositories.UserDao;
import com.rest.webservices.demo.exceptions.UserNotFoundException;

@RestController
public class PostController {
	
	@Autowired
	UserDao userDao; 
	
	@Autowired
	PostDao postDao; 
	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable(name = "id") int id) throws UserNotFoundException{
		User user = userDao.getUserById(id); 
		
		if(user == null) throw new UserNotFoundException("Id -> " + id + " not found");
		
		return user.getPosts(); 
			
	}	
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable(name = "id") int id, @RequestBody Post post) throws UserNotFoundException{
		
		User user = userDao.getUserById(id);
		
		if(user == null) throw new UserNotFoundException("Id -> " + id + " not found");
		
		user.addPost(post);
		
		post.setUser(user);
		
		postDao.save(post); 
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build(); 
	}
}
