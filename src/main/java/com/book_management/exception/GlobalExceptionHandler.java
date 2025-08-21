package com.book_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler( BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex){
		
		ErrorResponse response = new ErrorResponse(ex.getMessage(), false);
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
}
