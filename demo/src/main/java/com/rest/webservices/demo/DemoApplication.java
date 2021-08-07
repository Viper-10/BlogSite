package com.rest.webservices.demo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rest.webservices.demo.Entity.Post;
import com.rest.webservices.demo.Entity.User;
import com.rest.webservices.demo.Repositories.PostDao;
import com.rest.webservices.demo.Repositories.UserDao;

import net.bytebuddy.asm.Advice.This;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	UserDao userDao;
	
	@Autowired
	PostDao postDao;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User priyadharshan = new User("Priyadharshan", "Snake", new Date()); 
		User sara = new User("Sara", "Hello", new Date());
		User theka = new User("Theka", "Worlds", new Date());
		
		Post post1 = new Post("Quote", "We never so much underrate any duty as much as the duty of being happy"); 
		Post post2 = new Post("Wisdom", "I would rather die of passion than of boredom");
		Post post3 = new Post("Love", "All I wanted was to serve you");
		Post post4 = new Post("Quote", "A lion doesn't concern himself with the opinions of a sheep");
		
		userDao.addUser(priyadharshan);
		userDao.addUser(sara);
		userDao.addUser(theka);
		
		User StoredObjectOfPriyadharshan = userDao.getUserById(1);
		User StoredObjectOfSara = userDao.getUserById(2);
		
//		logger.info("\n\npd -> {}", StoredObjectOfPriyadharshan.toString());
//		StoredObjectOfPriyadharshan.addPost(post1);
//		StoredObjectOfPriyadharshan.addPost(post2);
//		StoredObjectOfPriyadharshan.addPost(post3);
//		StoredObjectOfSara.addPost(post4);

//		post1.setUser(StoredObjectOfPriyadharshan);
//		post2.setUser(StoredObjectOfPriyadharshan);
//		post3.setUser(StoredObjectOfPriyadharshan);
//		post4.setUser(StoredObjectOfSara);		
//		
//		postDao.save(post1); 
//		postDao.save(post2); 
//		postDao.save(post3); 
//		postDao.save(post4); 
		
		logger.info("\nnAll users -> {}\n\n\n", userDao.getAllUsers());
		logger.info("\nnAll posts -> {}\n\n\n", postDao.findAll());
		
	}	
}
