package com.sprint.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
