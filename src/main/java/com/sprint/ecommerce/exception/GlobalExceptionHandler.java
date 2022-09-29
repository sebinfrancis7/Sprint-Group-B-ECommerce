package com.sprint.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.PSQLException;
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
		return new ResponseEntity<String>("404\n" + notFound.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AlreadyExistsException.class)
	public ResponseEntity<String> alreadyExists(AlreadyExistsException alreadyExists) {
		return new ResponseEntity<String>("409\n" + alreadyExists.getMessage(), HttpStatus.CONFLICT);
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

	@ExceptionHandler(value = UniqueValueException.class)
	public ResponseEntity<String> alreadyExists(UniqueValueException uniqueValue) {
		return new ResponseEntity<String>("409\n" + uniqueValue.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = MismatchException.class)
	public ResponseEntity<String> alreadyExists(MismatchException missMatch) {
		return new ResponseEntity<String>("401\n" + missMatch.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = PSQLException.class)
	public ResponseEntity<String> handleSqlException(PSQLException ex) {
		return new ResponseEntity<String>("409\nSQL Exception\n" + ex.getMessage(), HttpStatus.CONFLICT);
	}

}
