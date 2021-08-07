package com.rest.webservices.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Controller advice so it is applicable to all the controllers. 
@ControllerAdvice
@RestController
public class CustomisedResponseEntityHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	/* 
	 * Handles all Exceptions
	 * returns  http status 500(Internal server error)
	 *  */
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) 
			throws Exception{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)); 	
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	/* 
	 * Handles UserNotFoundException
	 *  returns http status 404 (Page not found) 
	 *  */
	
	public final ResponseEntity<Object> handleUserNotFoundException (UserNotFoundException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)); 	
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
			

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Name not valid", ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}