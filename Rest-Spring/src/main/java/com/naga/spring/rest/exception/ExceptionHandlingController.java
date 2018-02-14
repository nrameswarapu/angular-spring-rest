package com.naga.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException excep){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Not Found");
		response.setErrorMessage(excep.getMessage());
		
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException excep){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Validation Error");
		response.setErrorMessage(excep.getMessage());
		
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
}
