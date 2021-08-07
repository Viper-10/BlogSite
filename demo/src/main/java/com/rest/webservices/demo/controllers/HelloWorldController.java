package com.rest.webservices.demo.controllers;
import com.rest.webservices.demo.Entity.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.demo.Entity.HelloWorldBean;

@RestController
public class HelloWorldController {
	
//	@RequestMapping(value = "/hello-world")
	@GetMapping(value = "/hello-world")
	public String handleHelloWorld() {
		return "Hello world"; 
	}
	
	@GetMapping(value = "/hello-world-bean")
	public HelloWorldBean handleHelloWorldBean() {
		return new HelloWorldBean("Priyadharshan is here");
	}
}
