package com.rest.webservices.demo.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Post {	
	@GeneratedValue
	@Id
	private int id; 
	
	private String topic;
	private String content; 

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	public Post() {
	}	

	public Post(String topic, String content) {
		this.topic = topic;
		this.content = content;
	}
	
	public Post(String topic, String content, User user) {
		this.topic = topic;
		this.content = content;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* We shouldn't select user for generating toString()
	 * because then we'll form a circular loop of user.toString() trying to print
	 * post, and post.toString() trying to print User. */
	 
	@Override
	public String toString() {
		return "\nPost [id=" + id + ", topic=" + topic + ", content=" + content + "]\n";
	}
	
}
