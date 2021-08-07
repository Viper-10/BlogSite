package com.rest.webservices.demo.Entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"password", "id"})
@NamedQuery(name = "get_all_users", query = "select u from User u")
public class User {	
//	@JsonIgnore
	@GeneratedValue
	@Id
	private int id; 
	
	@Size(min = 2, message = "Name should atleast have a size of 2")
	private String name; 	
	
	// mappedby is used to say which attribute conveys the relationship in the other entity(Here 
	// in posts user attribute uniquely identifies the user it belongs to, so it is the mappedby value here).
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts = new LinkedList<>();
	
	// This will ignore the attribute  when we are sending this as a response
	// back to the browser(When Jackson is converting from bean to JSON, it doesn't include it.	
	// If there are multiple attributes to exclude we can use @JsonIgnoreProperties above the class name 
	
//	@JsonIgnore
	
	private String password; 
	
	// birthdate should always be in the past. 
	@Past
	@Column(name = "birth_date")
	private Date birthDate;
	
	{
		birthDate = new Date();
	}
	
	public User() {
	}
	
	public User(int id, @Size(min = 2, message = "Name should atleast have a size of 2") String name, String password,
			@Past Date birthDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthDate = birthDate;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password; 
	}
	
	public User(String name, String password, Date date) {
		this.name = name;
		this.password = password; 
		this.birthDate = date; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setDate(Date date) {
		this.birthDate = date;
	} 
	
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", birthdate=" + birthDate + "]\n";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void addPost(Post posts) {
		this.posts.add(posts);
	}

}
