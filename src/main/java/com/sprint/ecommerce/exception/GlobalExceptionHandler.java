package com.sprint.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<String> notFound(NotFoundException notFound) {
		return new ResponseEntity<String>("Not Found Exception", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = AlreadyExistsException.class)
	public ResponseEntity<String> alreadyExists(AlreadyExistsException alreadyExists) {
		return new ResponseEntity<String>("Already Exists Exception", HttpStatus.CONFLICT);
	}
}
