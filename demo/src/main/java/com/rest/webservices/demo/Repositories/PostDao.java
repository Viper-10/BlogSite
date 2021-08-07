package com.rest.webservices.demo.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservices.demo.Entity.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
	
}
