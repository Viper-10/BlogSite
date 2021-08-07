package com.rest.webservices.demo.Repositories;

import com.rest.webservices.demo.Entity.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
		
	@PersistenceContext
	EntityManager entityManager; 
	
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("get_all_users", User.class); 
		return query.getResultList(); 
	}
	
	public User getUserById(int id) {
		return entityManager.find(User.class, id); 
	}
	
	public User addUser(User user) {
		 User savedUser = entityManager.merge(user); 
		 return savedUser; 
	}
	
	public boolean deleteUserById(int id) {
		User user = getUserById(id);
		
		if(user == null) {
			return false; 
		}else {
			entityManager.remove(user);
			return true; 
		}		
	}
}
